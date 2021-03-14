package edu.baylor.ecs.cloudhubs.semantics;

//import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
//import edu.baylor.ecs.cloudhubs.semantics.util.MainDriver;
//import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
//import edu.baylor.ecs.cloudhubs.semantics.util.ProcessFiles;
//import edu.baylor.ecs.cloudhubs.semantics.util.factory.*;
//import edu.baylor.ecs.cloudhubs.semantics.util.file.CacheManager;
//import io.quarkus.runtime.QuarkusApplication;
//import io.quarkus.runtime.annotations.QuarkusMain;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

//@QuarkusMain
public class SemanticAnalysisCommand { //implements QuarkusApplication {
//
//    public static String sutPath;
//    public static String cachePath;
//
//    @Override
//    public int run(String... args) throws Exception {
//
////        MainDriver md = new MainDriver();
////        md.process(args);
//        long start = System.currentTimeMillis();
//        List<String> paths = new ArrayList<>();
//        Path p = Paths.get("/home/jan/Development/stress-tests");
//        try (Stream<Path> stream = Files.walk(p, 1)) {
//            List<String> collect = stream
//                    .map(String::valueOf)
//                    .sorted()
//                    .collect(Collectors.toList());
//            for (int i = 1; i < collect.size(); i++) {
//                paths.add(collect.get(i));
//            }
//
//        }
//
////        String[] split = args[0].split(",");
//        List<Long> times = new ArrayList<>();
//        for (String path: paths
//             ) {
//            for (int i = 0; i < 10; i++) {
//                args[0] = path + ",/home/jan/Development/cache";
//                long st = System.currentTimeMillis();
//                MainDriver md = new MainDriver();
//                md.process(args);
//                times.add(System.currentTimeMillis() - st);
//            }
//            long sumTime = 0;
//            for (Long t: times
//                 ) {
//                sumTime += t;
//            }
//            String[] split = path.split("/");
//            System.out.println(split[split.length-1] + "," + sumTime/10);
//            times = new ArrayList<>();
//        }
//
//
////        initCache();
////        initPaths(args);
////        preProcess();
////        processCodeClonesFromCache();
////        conductCalculation();
////        persistCache();
////        System.out.println(System.currentTimeMillis() - start);
//        return 0;
//    }
//
//    private void persistCache() {
//        CacheManager cacheManager = new CacheManager();
//        cacheManager.persistCache(cachePath);
//    }
//
//    private void conductCalculation() {
//        ModuleCloneFactory moduleCloneFactory = new ModuleCloneFactory();
//        moduleCloneFactory.createData();
//    }
//
//    private void initPaths(String... args) {
//        String[] split = args[0].split(",");
//        sutPath = split[0];
//        cachePath = split[1];
////        System.out.println(sutPath + " " + cachePath);
////        sutPath = "/Users/jan/Development/train-ticket/";
////        cachePath = "/Users/jan/Development/data/";
////        sutPath = "C:\\git\\train-ticket";
////        cachePath = "C:\\git\\data";
//    }
//
//
//    public void initCache(){
//        MsCache.init();
//        EntityCache.init();
//    }
//
//    public void preProcess() {
//        ProcessFiles.run(sutPath);
//        FlowBuilder flowBuilder = new FlowBuilder();
//        flowBuilder.buildFlows();
//    }
//
//    public void processCodeClonesFromCache() {
////        CacheManager cacheManager = new CacheManager();
////        cacheManager.recreateCache(cachePath);
//        CodeClonesFactory codeClonesFactory = new CodeClonesFactory();
//        codeClonesFactory.findCodeClones();
//        ModuleClonePairFactory mcpf = new ModuleClonePairFactory();
//        mcpf.printModuleClonePairs();
//    }

}
