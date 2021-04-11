package edu.baylor.ecs.cloudhubs.examiner.services;

import edu.baylor.ecs.cloudhubs.examiner.data.AppCache;
import edu.baylor.ecs.cloudhubs.examiner.data.CodeClonesData;
import edu.baylor.ecs.cloudhubs.examiner.data.InconsistencyData;

public class AppCacheFactory {

    private static AppCache appCache;

    public static void init() {
        AppCacheFactory.appCache = new AppCache();
    }

    public static void add(String id, String path, String variable) {

    }

    public static void addInconsistencyData(InconsistencyData inconsistencyData) {
        AppCacheFactory.appCache.addInconsistencyData(inconsistencyData);
    }

    public static void addCodeClonesData(CodeClonesData codeClonesData) {
        AppCacheFactory.appCache.addCodeClonesData(codeClonesData);
    }

    public static AppCache getAppCache() {
        return appCache;
    }

}
