package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsClassRoles;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;
import edu.baylor.ecs.cloudhubs.semantics.util.visitor.MsVisitor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class ProcessFiles {

    public static boolean isJava = false;

    public static boolean detectJavaFiles(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            isJava = true;
        }).explore(projectDir);
        boolean toReturn = isJava;
        isJava = false;
        return toReturn;
    }

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
            if (path.contains("entity") && (!path.contains("Test"))) {
                role = MsClassRoles.ENTITY;
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
                } else if (role.equals(MsClassRoles.ENTITY)) {
                    // CLASS
                    MsVisitor.visitClass(file, path, role, msId);
                    // FIELDS
                    MsVisitor.visitFields(file, path, msId);
                    // ENTITY CLASS
                    MsVisitor.visitEntityClass(file, path, role, msId);
                    // ENTITY FIELD
                    MsVisitor.visitEntityFields(file, path, msId);
                }
            } else {
//                System.out.println();
            }
        }).explore(projectDir);
        // PRINT CACHE
    }

//    public static void run(String[] paths) {
//        List<String> dire
//        for (String s: paths
//             ) {
//            String myDirectoryPath = s;
//            File file = new File(myDirectoryPath);
//            String[] directories = file.list(new FilenameFilter() {
//                @Override
//                public boolean accept(File current, String name) {
//                    boolean isDirectory = new File(current, name).isDirectory();
//                    boolean isModule = name.contains("ts");
//                    return isDirectory && isModule;
//                }
//            });
//
//        }
//    }

    public static void run(String path) {

        String myDirectoryPath = path;
        File file = new File(myDirectoryPath);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                File currentFile = new File(current, name);
                boolean isDirectory = currentFile.isDirectory();
                //boolean isModule = name.contains("ts");
                boolean isModule = false;
                if (isDirectory) {
                    isModule = detectJavaFiles(currentFile);
                }
                return isDirectory && isModule;
            }
        });
        MsCache.modules = Arrays.asList(directories);
        File projectDir = new File(path);
        processFile(projectDir);
        System.out.println();
    }
}