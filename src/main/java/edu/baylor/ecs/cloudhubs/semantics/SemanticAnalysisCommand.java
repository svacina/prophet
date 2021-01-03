package edu.baylor.ecs.cloudhubs.semantics;

import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
import edu.baylor.ecs.cloudhubs.semantics.util.ProcessFiles;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.CodeClonesFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowsFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.ModuleClonePairFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.file.CacheManager;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class SemanticAnalysisCommand implements QuarkusApplication {

    private String sutPath;
    private String cachePath;

    @Override
    public int run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        initCache();
        initPaths(args);
//        preProcess();
        processCodeClonesFromCache();
        System.out.println(System.currentTimeMillis() - start);
        return 0;
    }

    private void initPaths(String... args) {
//        String[] split = args[0].split(",");
//        sutPath = split[0];
        sutPath = "/Users/jan/Development/train-ticket/";
        cachePath = "/Users/jan/Development/data/";
        sutPath = "C:\\git\\train-ticket";
        cachePath = "C:\\git\\data";
    }


    public void initCache(){
        MsCache.init();
    }

    public void preProcess() {
        ProcessFiles.run(sutPath);
        FlowBuilder flowBuilder = new FlowBuilder();
        flowBuilder.buildFlows();
        CacheManager cacheManager = new CacheManager();
        cacheManager.persistCache(cachePath);
    }

    public void processCodeClonesFromCache() {
        CacheManager cacheManager = new CacheManager();
        cacheManager.recreateCache(cachePath);
        CodeClonesFactory codeClonesFactory = new CodeClonesFactory();
        codeClonesFactory.findCodeClones();
        ModuleClonePairFactory mcpf = new ModuleClonePairFactory();
        mcpf.createModuleClonePairs();
    }

}
