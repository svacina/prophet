package edu.baylor.ecs.cloudhubs.semantics.util.file;

import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzedCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsCodeClone;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzeSutArgs;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.CodeClonesFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.builder.FlowBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.EntityClusterFactory;

public class MainService {

    public AnalyzedCache analyze(AnalyzeSutArgs analyzeSutArgs) {
        MsCacheService.init();
        EntityCache.init();
        PathService.sutPath = analyzeSutArgs.getSutRoot();
        PathService.cachePath = analyzeSutArgs.getDataOutputPath();
        ProcessService.run(PathService.sutPath);
        processCodeClones();
        processInconsistencies();
        if (analyzeSutArgs.isLogResults()) {
            persistCache();
        }
        if (analyzeSutArgs.isLogStats()) {
            printStats();
        }
        return new AnalyzedCache(MsCacheService.typeA, MsCacheService.typeB, EntityCache.entityClusterList);
    }

    public void process(String... args){
        // cache
        MsCacheService.init();
        EntityCache.init();
        // paths
        String[] split = args[0].split(",");
        PathService.sutPath = split[0];
        PathService.cachePath = split[1];
        // basic files
        ProcessService.run(PathService.sutPath);
        // code clones
        processCodeClones();
        // print code clones
        printCodeClones();
        // inconsistencies
         processInconsistencies();
        // cache
        // persistCache();
    }

    private void printStats() {
        String stats = StatService.printToString();
        CacheService cacheService = new CacheService();
        cacheService.persistStats(PathService.cachePath, stats);
    }

    public void printCodeClones(){
        for (MsCodeClone msCodeClone : MsCacheService.typeB
             ) {
            StringBuilder sb = new StringBuilder();
            sb.append(msCodeClone.getA().getMsController().getClassName());
            sb.append(".");
            sb.append(msCodeClone.getA().getMsControllerMethod().getMethodName());
            sb.append("->");
            sb.append(msCodeClone.getB().getMsController().getClassName());
            sb.append(".");
            sb.append(msCodeClone.getB().getMsControllerMethod().getMethodName());
//            System.out.println(sb.toString());
        }
    }

    public void processCodeClones() {
        // flows
        FlowBuilder flowBuilder = new FlowBuilder();
        flowBuilder.buildFlows();
        // find code clones
        CodeClonesFactory codeClonesFactory = new CodeClonesFactory();
        codeClonesFactory.findCodeClones();
        // analyze code clones
//        ModuleClonePairFactory mcpf = new ModuleClonePairFactory();
//        mcpf.printModuleClonePairs();
//        mcpf.addIds();
    }


    public void processInconsistencies() {
        EntityClusterFactory ecm = new EntityClusterFactory();
        ecm.generateDefects();
    }

    private void persistCache() {
        CacheService cacheService = new CacheService();
        cacheService.persistCache(PathService.cachePath);
        cacheService.persistInconsistencies(PathService.cachePath);
    }
}
