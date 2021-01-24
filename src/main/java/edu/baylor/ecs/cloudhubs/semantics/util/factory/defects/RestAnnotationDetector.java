package edu.baylor.ecs.cloudhubs.semantics.util.factory.defects;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;

public class RestAnnotationDetector {
    public static void missingRestAnnotation(MethodDeclaration n, MsId msId){
        if (msId.getPath().contains("controller")) {
            boolean contains = false;
            for (AnnotationExpr a: n.getAnnotations()
                 ) {

                if (a.getNameAsString().contains("Mapping")) {
                    contains = true;
                }
            }
            if (!contains) {
                String a = "no mapping";
            }
        }
    }

    // missing cross origin

    // cross origin same value

    // missing path in mapping

    // get mapping only request header in the arguments

    // {} -> path variable

    // missing logger in the statements

    // path must begin with the slash

    // controller has field repository
}
