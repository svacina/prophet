package edu.baylor.ecs.cloudhubs.semantics.util.visitor;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import edu.baylor.ecs.cloudhubs.semantics.checkers.controller.RequestHeaderChecker;
import edu.baylor.ecs.cloudhubs.semantics.entity.*;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.MsRestCallFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MsVisitor {

    public static void visitClass(File file, String path) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    MsClass msClass = new MsClass();
                    msClass.setClassName(n.getNameAsString());
                    Optional<Node> parentNode = n.getParentNode();
                    if (parentNode.isPresent()) {
                        CompilationUnit cu = (CompilationUnit) parentNode.get();
                        Optional<PackageDeclaration> pd = cu.getPackageDeclaration();
                        pd.ifPresent(packageDeclaration -> msClass.setPackageName(packageDeclaration.getNameAsString()));
                    }
                    NodeList<AnnotationExpr> nl = n.getAnnotations();
                    for (AnnotationExpr annotationExpr : nl) {
                        if (annotationExpr.getNameAsString().equals("Service")){
                            msClass.setRole(MsClassRoles.SERVICE);
                        }
                        if (annotationExpr.getNameAsString().equals("RestController")){
                            msClass.setRole(MsClassRoles.CONTROLLER);
                            // get annotation request mapping and value
                        }
                        if (annotationExpr.getNameAsString().equals("Repository")){
                            msClass.setRole(MsClassRoles.REPOSITORY);
                        }
                    }
                    if (nl.size() == 0 && n.getNameAsString().contains("Service")) {
                        msClass.setRole(MsClassRoles.SERVICE_INTERFACE);
                    }

                    msClass.setIds();
                    msClass.setPath(path);
                    MsCache.addMsClass(msClass);
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitMethods(File file, MsClassRoles role, String path) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodDeclaration n, Object arg) {
                    super.visit(n, arg);
                    if (role.equals(MsClassRoles.CONTROLLER)) {
                        // (1) Missing '@RequestHeader' annotation (method)
                        RequestHeaderChecker.check(n);
                    }

                    MsMethod msMethod = new MsMethod();
                    msMethod.setReturnType(n.getTypeAsString());
                    msMethod.setMethodName(n.getNameAsString());
                    msMethod.setLine(n.getBegin().get().line);
                    NodeList<Modifier> modifiers = n.getModifiers();
                    NodeList<Parameter> parameters = n.getParameters();
                    // ToDo: set arguments
                    parameters.stream().forEach(e -> {
                        msMethod.addArgument(new MsArgument(e.getTypeAsString()));
                    });
                    Optional<Node> parentNode = n.getParentNode();
                    if (parentNode.isPresent()) {
                        // Set Class
                        ClassOrInterfaceDeclaration cl = (ClassOrInterfaceDeclaration) parentNode.get();
                        msMethod.setClassName(cl.getName().getIdentifier());
                        // Find Package
                        parentNode = parentNode.get().getParentNode();
                        if (parentNode.isPresent()) {
                            // Set Package
                            CompilationUnit cu = (CompilationUnit) parentNode.get();
                            Optional<PackageDeclaration> pd = cu.getPackageDeclaration();
                            pd.ifPresent(packageDeclaration -> msMethod.setPackageName(packageDeclaration.getNameAsString()));
                        }
                    }
                    msMethod.setIds();
                    msMethod.setPath(path);
                    MsCache.addMsMethod(msMethod);
                }
            }.visit(StaticJavaParser.parse(file), null);
            // System.out.println(); // empty line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitMethodCalls(File file, String path) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodCallExpr n, Object arg) {
                    super.visit(n, arg);
                    Optional<Expression> scope = n.getScope();
                    if (scope.isPresent()) {
                        if (scope.get() instanceof  NameExpr) {
                            // get common properties
                            int lineNumber = n.getBegin().get().line;
                            // decide between service / restTemplate
                            NameExpr fae = scope.get().asNameExpr();
                            String name = fae.getNameAsString();
                            if (name.equals("service")) {
                                // service is being called
                                MsMethodCall msMethodCall = new MsMethodCall();

                                msMethodCall.setLineNumber(lineNumber);
                                msMethodCall.setStatementDeclaration(n.toString());
                                msMethodCall.setMsParentMethod(MsParentVisitor.getMsParentMethod(n));
                                msMethodCall.setCalledServiceId(name);
                                MethodCallExpr methodCallExpr = (MethodCallExpr) fae.getParentNode().get();
                                msMethodCall.setCalledMethodName(methodCallExpr.getNameAsString());
                                msMethodCall.setParentClassId();
                                // register method call to cache
                                MsCache.addMsMethodCall(msMethodCall);
                            } else if (name.equals("restTemplate")) {
                                // rest template is being called
                                MsRestCall msRestCall = MsRestCallFactory.getMsRestCall(n);
                                msRestCall.setLineNumber(lineNumber);
                                msRestCall.setMsParentMethod(MsParentVisitor.getMsParentMethod(n));
                                msRestCall.setParentClassId();
                                msRestCall.setPath(path);
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



    public static void visitFields(File file, String path) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(FieldDeclaration n, Object arg) {
                    super.visit(n, arg);
                    MsFieldVisitor.visitFieldDeclaration(n, path);
                }
            }.visit(StaticJavaParser.parse(file), null);
            // System.out.println(); // empty line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
