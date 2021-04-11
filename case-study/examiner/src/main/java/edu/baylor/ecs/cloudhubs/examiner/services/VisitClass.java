package edu.baylor.ecs.cloudhubs.examiner.services;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;

public class VisitClass {
    public static void visitClass(File file, String path) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    VisitComment.detect(n, path);
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
