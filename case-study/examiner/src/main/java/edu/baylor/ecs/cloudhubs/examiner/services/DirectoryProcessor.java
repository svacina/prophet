package edu.baylor.ecs.cloudhubs.examiner.services;

import java.io.File;

public class DirectoryProcessor {

    public void processSut() {
        File projectDir = new File(CmdFactory.cmdArgs.getSut());
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            VisitClass.visitClass(file, path);
            VisitField.visitField(file, path);
        }).explore(projectDir);
    }
}
