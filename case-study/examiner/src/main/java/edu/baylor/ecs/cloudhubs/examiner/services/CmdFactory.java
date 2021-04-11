package edu.baylor.ecs.cloudhubs.examiner.services;

import edu.baylor.ecs.cloudhubs.examiner.data.CmdArgs;

public class CmdFactory {

    public static CmdArgs cmdArgs;

    public static void createCmdArgs(String... args) {
        CmdArgs cmdArgs = new CmdArgs();
        String[] split = args[0].split(",");
        cmdArgs.setSut(split[0]);
        cmdArgs.setData(split[1]);
        if (split[2].equals("-i")){
            cmdArgs.setAnalyzeInconsistency(true);
        } else {
            cmdArgs.setAnalyzeCodeClone(true);
        }
        CmdFactory.cmdArgs = cmdArgs;
    }
}
