package edu.baylor.ecs.cloudhubs.semantics.util;

import com.github.javaparser.ParseException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import edu.baylor.ecs.cloudhubs.semantics.entity.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MsVisitor {

    public static void visitClass(File file) {
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
                        }
                        if (annotationExpr.getNameAsString().equals("Repository")){
                            msClass.setRole(MsClassRoles.REPOSITORY);
                        }
                    }
                    msClass.setIds();
                    MsCache.addMsClass(msClass);
                }
            }.visit(StaticJavaParser.parse(file), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void visitMethods(File file, MsClassRoles role) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodDeclaration n, Object arg) {
                    super.visit(n, arg);
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
                    MsCache.addMsMethod(msMethod);
                }
            }.visit(StaticJavaParser.parse(file), null);
            System.out.println(); // empty line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void visitNode(File file, Node node) {
        try {
            new NodeIterator(new NodeIterator.NodeHandler() {
                @Override
                public boolean handle(Node node) {
                    if (node instanceof Statement) {
                        Statement st = (Statement) node;
                        System.out.println(" [Lines " + node.getBegin().get().line + " - " + node.getEnd().get().line + " ] " + node);
                        return false;
                    } else {
                        return true;
                    }
                }
            }).explore(StaticJavaParser.parse(file));
        } catch (IOException e) {
            new RuntimeException(e);
        }
    }

    public static void visitMethodCalls(File file) {
        try {
            new VoidVisitorAdapter<Object>() {
                @Override
                public void visit(MethodCallExpr n, Object arg) {
                    super.visit(n, arg);
                    MsMethodCall msMethodCall = new MsMethodCall();
                    // Line number
                    System.out.println(" [L " + n.getBegin().get().line + "] " + n);
                    msMethodCall.setLineNumber(n.getBegin().get().line);
                    Optional<Expression> scope = n.getScope();
                    if (scope.isPresent()) {
                        if (scope.get() instanceof  NameExpr) {
                            NameExpr fae = scope.get().asNameExpr();
                            String name = fae.getNameAsString();
                            if (name.equals("service")) {
                                msMethodCall.setCalledServiceId(name);
                                //
                            }

                            if (name.equals("restTemplate")) {

                                // here try to print the n
                                visitNode(file, n);



                                msMethodCall.setCalledServiceId(name);
                                Optional<Node> parentNode = n.getParentNode();
                                if (parentNode.isPresent()) {
                                    if (parentNode.get() instanceof VariableDeclarator) {
                                        VariableDeclarator vd = (VariableDeclarator) parentNode.get();
                                        // getLine
                                        vd.getTokenRange();
                                        // get
                                        Optional<Expression> oe = vd.getInitializer();
                                        // if present, if instance of
                                        MethodCallExpr mce = (MethodCallExpr) oe.get();

                                    }
                                }
                                //
                            }


                                // ** find method, class, package
                            Optional<Node> parentNode = n.getParentNode();

                            if (parentNode.isPresent()) {
                                for (Node ln: parentNode.get().getChildNodes()){
                                    if (ln instanceof MethodCallExpr) {
                                        MethodCallExpr mce = (MethodCallExpr) ln;
                                        System.out.println(mce.getName().getIdentifier());
                                    }
                                }
                            }

                            // Find Method
                            while (parentNode.isPresent() && !(parentNode.get() instanceof MethodDeclaration)) {
                                parentNode = parentNode.get().getParentNode();
                            }
                            if (parentNode.isPresent()) {
                                // Set Method
                                MethodDeclaration md = (MethodDeclaration) parentNode.get();
                                System.out.println(md.getName().getIdentifier());
                                msMethodCall.setMethodName(md.getName().getIdentifier());
                                //Find Class
                                while (parentNode.isPresent() && !(parentNode.get() instanceof ClassOrInterfaceDeclaration)) {
                                    parentNode = parentNode.get().getParentNode();
                                }
                                if (parentNode.isPresent()) {
                                    // Set Class
                                    ClassOrInterfaceDeclaration cl = (ClassOrInterfaceDeclaration) parentNode.get();
                                    System.out.println(cl.getName().getIdentifier());
                                    msMethodCall.setClassName(cl.getName().getIdentifier());
                                    // Find Package
                                    parentNode = parentNode.get().getParentNode();
                                    if (parentNode.isPresent()) {
                                        // Set Package
                                        CompilationUnit cu = (CompilationUnit) parentNode.get();
                                        Optional<PackageDeclaration> pd = cu.getPackageDeclaration();
                                        if (pd.isPresent()) {
                                            System.out.println(pd.get().getName());
                                            msMethodCall.setPackageName(pd.get().getNameAsString());
                                        }
                                    }
                                }
                            }
                            MsCache.addMsMethodCall(msMethodCall);
                            // ** end method, class, package
                        }
                    }



                }
            }.visit(StaticJavaParser.parse(file), null);
            System.out.println(); // empty line
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // field

//            try {
//                new VoidVisitorAdapter<Object>() {
//                    @Override
//                    public void visit(FieldDeclaration n, Object arg) {
//                        super.visit(n, arg);
//                        MsField msField = new MsField();
//                        VariableDeclarator vd = n.getVariables().get(0);
//                        vd.getName().getIdentifier();
//                        Optional<Node> parentNode = n.getParentNode();
//                        while (parentNode.isPresent() && !(parentNode.get() instanceof ClassOrInterfaceDeclaration)) {
//                            parentNode = parentNode.get().getParentNode();
//                        }
//                        if (parentNode.isPresent()) {
//                            // Set Class
//                            ClassOrInterfaceDeclaration cl = (ClassOrInterfaceDeclaration) parentNode.get();
//                            msField.setClassName(cl.getName().getIdentifier());
//                            // Find Package
//                            parentNode = parentNode.get().getParentNode();
//                            if (parentNode.isPresent()) {
//                                // Set Package
//                                CompilationUnit cu = (CompilationUnit) parentNode.get();
//                                Optional<PackageDeclaration> pd = cu.getPackageDeclaration();
//                                if (pd.isPresent()) {
//                                    msField.setPackageName(pd.get().getNameAsString());
//                                }
//                            }
//                        }
//                    }
//                }.visit(StaticJavaParser.parse(file), null);
//                System.out.println(); // empty line
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

}
