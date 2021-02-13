package edu.baylor.ecs.cloudhubs.semantics.util;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsClassRoles;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;

import java.util.ArrayList;
import java.util.List;

public class MissingAnnotationDetector {

    private static final String[] annotationDeclaration = new String[]{"Service", "RestController", "Repository", "Data", "Controller"};

    public static void findMissingAnnotation(ClassOrInterfaceDeclaration n, MsId msId, MsClassRoles role) {
        //msId

        //classId

        //packageName

        //className

        //compare className with annotation list
        NodeList<AnnotationExpr> nl = n.getAnnotations();

        String componentAnnotation = "";
        String componentType = "";

        for (AnnotationExpr annotationExpr : nl) {
            for (String declaration: annotationDeclaration
                 ) {
                if (annotationExpr.getNameAsString().equals(declaration)){
                    componentAnnotation=annotationExpr.getNameAsString();
                }
            }
        }

        for (String declaration: annotationDeclaration
        ) {
            if (msId.getPath().contains(declaration)){
                componentType = declaration;
            }
        }

//        if (!componentAnnotation.contains(componentType)){
//            System.out.println("unequal");
//        }

        if (componentType.contains("Controller") && !msId.getPath().contains("Test")){
            if (componentAnnotation.contains("Controller")) {
                //System.out.println();
            } else {
                String str = "";
            }
        }

    }
}
