package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsClassRoles;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsId;
import edu.baylor.ecs.cloudhubs.semantics.util.visitor.MsVisitor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class ProcessFiles {

    public static void processFile(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
//            System.out.println(path);
//            System.out.println(Strings.repeat("=", path.length()));
            MsClassRoles role = null;
            if (path.contains("Controller") && (!path.contains("Test"))){
                role = MsClassRoles.CONTROLLER;
            }
            if (path.contains("Service") && (!path.contains("Test"))) {
                role = MsClassRoles.SERVICE;
            }
            if (path.contains("Repository") && (!path.contains("Test"))) {
                role = MsClassRoles.REPOSITORY;
            }
            MsId msId = new MsId(path);
            if (role != null) {
                if (role.equals(MsClassRoles.CONTROLLER) || role.equals(MsClassRoles.SERVICE)) {
                    // CLASS
                    MsVisitor.visitClass(file, path, role, msId);
                    // METHOD
                    MsVisitor.visitMethods(file, role, path, msId);
                    // METHOD CALLS
                    MsVisitor.visitMethodCalls(file, path, msId);
                    // FIELDS
                    MsVisitor.visitFields(file, path, msId);
                } else if (role.equals(MsClassRoles.REPOSITORY)){
                    // CLASS
                    MsVisitor.visitClass(file, path, role, msId);
                    // METHOD
                    MsVisitor.visitMethods(file, role, path, msId);
                } else if (path.contains("entity")) {
                    // visitFieldDeclaration
                }
            } else {
//                System.out.println();
            }
        }).explore(projectDir);
        // PRINT CACHE
    }

    public static void run(String[] args) {
        MsCache.init();
        String myDirectoryPath = args[0];
        File file = new File(myDirectoryPath);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                boolean isDirectory = new File(current, name).isDirectory();
                boolean isModule = name.contains("ts");
                return isDirectory && isModule;
            }
        });
        MsCache.modules = Arrays.asList(directories);
        File projectDir = new File(args[0]);
        processFile(projectDir);
        MsCache.print();
        System.out.println();
    }
}