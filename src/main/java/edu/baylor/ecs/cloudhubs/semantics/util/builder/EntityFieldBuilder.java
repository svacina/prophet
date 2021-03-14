package edu.baylor.ecs.cloudhubs.semantics.util.builder;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.MsEntityField;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;

import java.util.List;

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
            List<Node> ll = n.getChildNodes();
            for (Node node: ll
                 ) {
                if (node instanceof VariableDeclarator) {
                    VariableDeclarator vd = (VariableDeclarator) node;
                    msEntityField.setName(vd.getNameAsString());
                    Type type = vd.getType();
                    if (type instanceof ClassOrInterfaceType) {
                        ClassOrInterfaceType ci = (ClassOrInterfaceType) type;
                        msEntityField.setType(ci.getNameAsString());
                    }
                    if (type instanceof PrimitiveType) {
                        PrimitiveType pt = (PrimitiveType) type;
                        msEntityField.setType(pt.getType().toString());
                    }

                }
            }
            EntityCache.addEntityField(msEntityField);
        }

    }
}
