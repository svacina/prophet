package edu.baylor.ecs.cloudhubs.semantics.service;

import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzeSutArgs;
import edu.baylor.ecs.cloudhubs.semantics.dto.AnalyzeTime;
import edu.baylor.ecs.cloudhubs.semantics.util.file.MainService;
import edu.baylor.ecs.cloudhubs.semantics.util.file.CacheService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StressTestService {

    public static void doStressTests(AnalyzeTime analyzeTime){
        List<String> paths = new ArrayList<>();
        Path p = Paths.get(analyzeTime.getSutRoot());
        try (Stream<Path> stream = Files.walk(p, 1)) {
            List<String> collect = stream
                    .map(String::valueOf)
                    .sorted()
                    .collect(Collectors.toList());
            for (int i = 1; i < collect.size(); i++) {
                paths.add(collect.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Long> times = new ArrayList<>();
        List<String> results = new ArrayList<>();
        for (String path: paths
        ) {
            for (int i = 0; i < 10; i++) {
                long st = System.currentTimeMillis();
                MainService md = new MainService();
                md.analyze(new AnalyzeSutArgs(
                        path,
                        analyzeTime.getDataOutputPath(),
                        analyzeTime.isLogResults(),
                        analyzeTime.isLogStats()));
                times.add(System.currentTimeMillis() - st);
            }
            long sumTime = 0;
            for (Long t: times
            ) {
                sumTime += t;
            }
            String[] split = path.split("/");
            results.add(split[split.length-1] + "," + sumTime/10);
            times = new ArrayList<>();
        }
        CacheService cacheService = new CacheService();
        cacheService.persistTimes(analyzeTime.getTestOutputPath(), results);
    }
}
