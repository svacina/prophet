package edu.baylor.ecs.cloudhubs.semantics;

import edu.baylor.ecs.cloudhubs.semantics.util.ProcessFiles;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowsFactory;
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
//        String[] split = args[0].split(",");
//        sutPath = split[0];
        sutPath = "/Users/jan/Development/train-ticket/";
        cachePath = "/Users/jan/Development/data/";
        createCache(sutPath);
        analyzeCodeClones();
        System.out.println(System.currentTimeMillis() - start);
        return 0;
    }

    public void createCache(String path) {
        ProcessFiles.run(path);
        CacheManager cacheManager = new CacheManager();
        cacheManager.persistCache(cachePath);
    }

    public void analyzeCodeClones(){
        CacheManager cacheManager = new CacheManager();
        cacheManager.recreateCache(cachePath);
        FlowBuilder flowBuilder = new FlowBuilder();
        flowBuilder.buildFlows();
//        FlowsFactory.createFlows();
    }
}
