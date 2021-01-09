package edu.baylor.ecs.cloudhubs.semantics.util.factory.defects.builder;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.entity.data.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.entity.model.MsEntityField;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;

public class EntityFieldBuilder {

    public void find(FieldDeclaration n, MsId msId) {
        if (msId.getPath().contains("entity")) {
            MsEntityField msEntityField = new MsEntityField();
            msEntityField.setPath(msId.getPath());
            if (n.getTokenRange().isPresent()) {
                msEntityField.setCode(n.getTokenRange().get().toString());
            }
            if (n.getAnnotations().size() > 0) {
                for (AnnotationExpr a: n.getAnnotations()
                     ) {
                    msEntityField.addAnnotation("@" + a.getNameAsString());
                }
            }
            EntityCache.addEntityField(msEntityField);
        }

    }
}
