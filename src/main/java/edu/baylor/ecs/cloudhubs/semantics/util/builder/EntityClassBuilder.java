package edu.baylor.ecs.cloudhubs.semantics.util.builder;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.MsEntityClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;

public class EntityClassBuilder {
    public static void find(ClassOrInterfaceDeclaration n, MsId msId) {
        if (msId.getPath().contains("entity")) {
            MsEntityClass msEntityClass = new MsEntityClass();
            msEntityClass.setPath(msId.getPath());
            msEntityClass.setName(n.getNameAsString());
            if (n.getTokenRange().isPresent()) {
                msEntityClass.setCode(n.getTokenRange().get().toString());
                msEntityClass.setEnum(msEntityClass.getCode().equals("enum"));
            }
            if (n.getAnnotations().size() > 0) {
                for (AnnotationExpr a: n.getAnnotations()
                ) {
                    a.getNameAsString();
                    String name = a.getNameAsString();
                    if (name.equals("Data")) {
                        msEntityClass.setData(name);
                    }
                    if (name.equals("Document")) {
                        msEntityClass.setDocument(name);
                        for (Node node: a.getChildNodes()
                             ) {
                            if (node instanceof MemberValuePair) {
                                MemberValuePair mvp = (MemberValuePair) node;
                                msEntityClass.setCollectionName(mvp.getValue().toString());
                            }
                        }
                    }
                }
            }
            //
            EntityCache.entityClasses.add(msEntityClass);
        }
    }
}
