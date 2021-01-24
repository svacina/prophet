package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.CodeClonesFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.ModuleClonePairFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.file.CacheManager;
import edu.baylor.ecs.cloudhubs.semantics.util.file.PathManager;

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
        // inconsistencies
//        processInconsistencies();
        // cache
        persistCache();

    }

    public void processCodeClones() {
        // flows
        FlowBuilder flowBuilder = new FlowBuilder();
        flowBuilder.buildFlows();
        // find code clones
        CodeClonesFactory codeClonesFactory = new CodeClonesFactory();
        codeClonesFactory.findCodeClones();
        // analyze code clones
        ModuleClonePairFactory mcpf = new ModuleClonePairFactory();
        mcpf.printModuleClonePairs();
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
