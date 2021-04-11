package edu.baylor.ecs.cloudhubs.examiner.services;

import edu.baylor.ecs.cloudhubs.examiner.data.AppCache;

public class MainDriver {

    public void process(String ...args) {
        CmdFactory.createCmdArgs(args);
        AppCacheFactory.init();
        DirectoryProcessor directoryProcessor = new DirectoryProcessor();
        directoryProcessor.processSut();
        AppCache appCache = AppCacheFactory.getAppCache();
        FileManager fileManager = new FileManager();
        fileManager.writeFiles(appCache);
    }
}
