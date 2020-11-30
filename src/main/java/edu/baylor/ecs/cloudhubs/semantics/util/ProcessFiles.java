package edu.baylor.ecs.cloudhubs.semantics.util;

import com.google.common.base.Strings;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsClassRoles;

import java.io.File;

public class ProcessFiles {

    public static void processFile(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            MsClassRoles role = null;
            if (path.contains("Controller")){
                role = MsClassRoles.CONTROLLER;
            }
            if (path.contains("Service")) {
                role = MsClassRoles.SERVICE;
            }
            if (role != null) {
                if (role.equals(MsClassRoles.CONTROLLER) || role.equals(MsClassRoles.SERVICE)) {
                    // CLASS
//                MsVisitor.visitClass(file);
                    // METHOD
                    MsVisitor.visitMethods(file, role);
                    // METHOD CALLS
              MsVisitor.visitMethodCalls(file);
                }
                if (path.contains("Repository")){
                    // visitClassDeclaration
                    // visitMethodDeclaration
                }

                if (path.contains("entity")) {
                    // visitFieldDeclaration
                }
            }


        }).explore(projectDir);
        // PRINT CACHE
    }

    public static void run(String[] args) {
        MsCache.init();
        File projectDir = new File(args[0]);
        processFile(projectDir);
        MsCache.print();
        System.out.println();
    }
}