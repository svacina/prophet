package edu.baylor.ecs.cloudhubs.semantics.util.factory;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsParentMethod;

import java.lang.reflect.Method;
import java.util.Optional;

public class MsParentMethodFactory {

    public static MsParentMethod getMsParentMethod(MethodCallExpr n){

        MsParentMethod msParentMethod = new MsParentMethod();

        // ** find method, class, package
        Optional<Node> parentNode = n.getParentNode();

        // Find Method
        // ----- SAME FOR ALMOST ALL
        while (parentNode.isPresent() && !(parentNode.get() instanceof MethodDeclaration)) {
            parentNode = parentNode.get().getParentNode();
        }
        if (parentNode.isPresent()) {
            // Set Method
            MethodDeclaration md = (MethodDeclaration) parentNode.get();
            msParentMethod.setMethodName(md.getName().getIdentifier());
            //Find Class
            while (parentNode.isPresent() && !(parentNode.get() instanceof ClassOrInterfaceDeclaration)) {
                parentNode = parentNode.get().getParentNode();
            }
            if (parentNode.isPresent()) {
                // Set Class
                ClassOrInterfaceDeclaration cl = (ClassOrInterfaceDeclaration) parentNode.get();
                msParentMethod.setClassName(cl.getName().getIdentifier());
                // Find Package
                parentNode = parentNode.get().getParentNode();
                if (parentNode.isPresent()) {
                    // Set Package
                    CompilationUnit cu = (CompilationUnit) parentNode.get();
                    Optional<PackageDeclaration> pd = cu.getPackageDeclaration();
                    if (pd.isPresent()) {
                        msParentMethod.setPackageName(pd.get().getNameAsString());
                    }
                }
            }
        }
        // ** end method, class, package
        return msParentMethod;
    }
}
