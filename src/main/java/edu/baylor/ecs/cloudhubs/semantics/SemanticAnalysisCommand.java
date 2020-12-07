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
//        final String name = args.length > 0 ? String.join(" ", args) : "";
        createCache(args);
        System.out.println(System.currentTimeMillis() - start);
        return 0;
    }

    public void analyzeCodeClones(String... args){
        ProcessFiles.run(args);
        FlowsFactory.createFlows();
    }

    public void createCache(String... args) {
        ProcessFiles.run(args);
        CacheManager cacheManager = new CacheManager();
        cacheManager.persistCache("C:\\git\\cache\\");
    }
}
