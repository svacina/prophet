package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsCodeClone;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.CodeClonesFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.ModuleClonePairFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.file.CacheManager;
import edu.baylor.ecs.cloudhubs.semantics.util.file.PathManager;
import edu.baylor.ecs.cloudhubs.semantics.util.stats.StatManager;

public class MainDriver {

    public void process(String... args){
        // cache
        MsCache.init();
        EntityCache.init();
        // paths
        String[] split = args[0].split(",");
        PathManager.sutPath = split[0];
        PathManager.cachePath = split[1];
        // basic files
        ProcessFiles.run(PathManager.sutPath);
        // code clones
        processCodeClones();
        // print code clones
        printCodeClones();
        // inconsistencies
         processInconsistencies();
        // cache
        // persistCache();
    }

    private void processFlags(String... args){
        // statistics
        if (args[2].equals("-s")){
            printStats();
        }
        // stress tests
        if (args[2].equals("-t")) {
            // read all subdirectories in /home/jan/Development/stress-tests


        }
    }

    private void printStats() {
        StatManager.printToString();
    }

    public void printCodeClones(){
        for (MsCodeClone msCodeClone :MsCache.typeB
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
        EntityClusterManager ecm = new EntityClusterManager();
        ecm.generateDefects();
    }

    private void persistCache() {
        CacheManager cacheManager = new CacheManager();
        cacheManager.persistCache(PathManager.cachePath);
        cacheManager.persistInconsistencies(PathManager.cachePath);
    }
}
