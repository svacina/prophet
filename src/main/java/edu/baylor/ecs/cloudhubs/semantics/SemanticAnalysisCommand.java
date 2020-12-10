package edu.baylor.ecs.cloudhubs.semantics;

import edu.baylor.ecs.cloudhubs.semantics.util.ProcessFiles;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.FlowsFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.file.CacheManager;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class SemanticAnalysisCommand implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        String[] arr = new String[3];
        arr = args[0].split(",");
        if (arr[2].equals("read")) {
            createCache(arr);
        } else {
            analyzeCodeClones();
        }

        System.out.println(System.currentTimeMillis() - start);
        return 0;
    }

    public void analyzeCodeClones(String... args){
        CacheManager cacheManager = new CacheManager();
        cacheManager.recreateCache();
        FlowsFactory.createFlows();
    }

    public void createCache(String... args) {
        ProcessFiles.run(args);
        CacheManager cacheManager = new CacheManager();
        cacheManager.persistCache("C:\\git\\cache\\");
    }
}
