package edu.baylor.ecs.cloudhubs.semantics.util.visitor;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsClassRoles;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.defects.builder.EntityClassBuilder;

import java.util.Optional;

public class MsClassVisitor {

    /**
     * Find EntityClass
     * Find MsClass
     */
    public static void visitClass(ClassOrInterfaceDeclaration n, MsId msId, MsClassRoles role) {
        EntityClassBuilder.find(n, msId);
        findMsClass(n, msId, role);
    }

    private static void findMsClass(ClassOrInterfaceDeclaration n, MsId msId, MsClassRoles role) {
        MsClass msClass = new MsClass();
        msClass.setClassName(n.getNameAsString());
        Optional<Node> parentNode = n.getParentNode();
        if (parentNode.isPresent()) {
            CompilationUnit cu = (CompilationUnit) parentNode.get();
            Optional<PackageDeclaration> pd = cu.getPackageDeclaration();
            pd.ifPresent(packageDeclaration -> msClass.setPackageName(packageDeclaration.getNameAsString()));
        }
        NodeList<AnnotationExpr> nl = n.getAnnotations();
        msClass.setRole(role);
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
        msClass.setMsId(msId);
        MsCache.addMsClass(msClass);
    }
}
