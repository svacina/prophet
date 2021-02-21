package edu.baylor.ecs.cloudhubs.semantics.util.visitor;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.*;
import edu.baylor.ecs.cloudhubs.semantics.util.MissingAnnotationDetector;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
import edu.baylor.ecs.cloudhubs.semantics.util.constructs.MsMethodBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.MsRestCallFactory;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.defects.RestAnnotationDetector;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.defects.builder.EntityClassBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.defects.builder.EntityFieldBuilder;
import edu.baylor.ecs.cloudhubs.semantics.util.stats.StatManager;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * MsVisitor -> visitClass
 * MsVisitor -> visitMethod
 * MsVisitor -> visitMethodCalls
 * MsVisitor -> visitFields
 */
public class MsVisitor {

    public static void visitMissingAnnotation(File file, String path, MsClassRoles role, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    MissingAnnotationDetector.findMissingAnnotation(n, msId, role);
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitClass(File file, String path, MsClassRoles role, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    MsClassVisitor.visitClass(n, msId, role);
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitEntityClass(File file, String path, MsClassRoles role, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    EntityClassBuilder.find(n, msId);
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void visitMethods(File file, MsClassRoles role, String path, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodDeclaration n, Object arg) {
                    super.visit(n, arg);
                    MsMethodBuilder.buildMsMethod(n, role, path, msId);
                    RestAnnotationDetector.missingRestAnnotation(n, msId);
                    StatManager.methods += 1;
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitMethodCalls(File file, String path, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodCallExpr n, Object arg) {
                    super.visit(n, arg);
                    StatManager.methodCalls += 1;
                    Optional<Expression> scope = n.getScope();
                    if (scope.isPresent()) {
                        if (scope.get() instanceof  NameExpr) {
                            // get common properties
                            int lineNumber = n.getBegin().get().line;
                            // decide between service / restTemplate
                            NameExpr fae = scope.get().asNameExpr();
                            String name = fae.getNameAsString();
                            if (name.toLowerCase().contains("repository")){
                                MsMethodCall msMethodCall = new MsMethodCall();
                                msMethodCall.setCode(n.getTokenRange().get().toString());
                                msMethodCall.setLineNumber(lineNumber);
                                msMethodCall.setStatementDeclaration(n.toString());
                                msMethodCall.setMsParentMethod(MsParentVisitor.getMsParentMethod(n));
                                msMethodCall.setCalledServiceId(name);
                                MethodCallExpr methodCallExpr = (MethodCallExpr) fae.getParentNode().get();
                                msMethodCall.setCalledMethodName(methodCallExpr.getNameAsString());
                                msMethodCall.setParentClassId();
                                msMethodCall.setMsId(msId);
                                // register method call to cache
                                MsCache.addMsMethodCall(msMethodCall);
                            }
                            if (name.toLowerCase().contains("service")) {
                                // service is being called
                                MsMethodCall msMethodCall = new MsMethodCall();
                                msMethodCall.setCode(n.getTokenRange().get().toString());
                                msMethodCall.setLineNumber(lineNumber);
                                msMethodCall.setStatementDeclaration(n.toString());
                                msMethodCall.setMsParentMethod(MsParentVisitor.getMsParentMethod(n));
                                msMethodCall.setCalledServiceId(name);
                                MethodCallExpr methodCallExpr = (MethodCallExpr) fae.getParentNode().get();
                                msMethodCall.setCalledMethodName(methodCallExpr.getNameAsString());
                                msMethodCall.setParentClassId();
                                msMethodCall.setMsId(msId);
                                // register method call to cache
                                MsCache.addMsMethodCall(msMethodCall);
                            } else if (name.equals("restTemplate")) {
                                // rest template is being called
                                MsRestCall msRestCall = MsRestCallFactory.getMsRestCall(n);
                                msRestCall.setCode(n.getTokenRange().get().toString());
                                msRestCall.setLineNumber(lineNumber);
                                msRestCall.setMsParentMethod(MsParentVisitor.getMsParentMethod(n));
                                msRestCall.setParentClassId();
                                msRestCall.setMsId(msId);
                                MsCache.addMsRestMethodCall(msRestCall);
                            }
                        }
                    }
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void visitFields(File file, String path, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(FieldDeclaration n, Object arg) {
                    super.visit(n, arg);
                    MsFieldVisitor.visitFieldDeclaration(n, path, msId);
                    StatManager.fields += 1;
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitEntityFields(File file, String path, MsId msId) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(FieldDeclaration n, Object arg) {
                    super.visit(n, arg);
                    EntityFieldBuilder builder = new EntityFieldBuilder();
                    builder.find(n, msId);
                    StatManager.fields += 1;
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
