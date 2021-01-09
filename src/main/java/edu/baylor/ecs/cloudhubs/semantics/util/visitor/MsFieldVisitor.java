package edu.baylor.ecs.cloudhubs.semantics.util.visitor;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsField;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsId;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
import edu.baylor.ecs.cloudhubs.semantics.util.factory.defects.builder.EntityFieldBuilder;

/**
 * Creates MsField for CodeClones
 * Finds EntityField for Inconsistencies
 */
public class MsFieldVisitor {

    public static void visitFieldDeclaration(FieldDeclaration n, String path, MsId msId) {
        findEntityField(n, msId);
        createMsField(n, msId);
    }

    private static void createMsField(FieldDeclaration n, MsId msId){
        MsField msField = new MsField();
        if (n.getVariables().size() > 0) {
            VariableDeclarator vd = n.getVariables().get(0);
            String variableName = vd.getNameAsString();
            if (variableName.toLowerCase().contains("service") || variableName.toLowerCase().contains("repository")) {
                msField.setFieldVariable(vd.getNameAsString());
                if (vd.getType() != null) {
                    msField.setFieldClass(vd.getTypeAsString());
                    msField.setParentMethod(MsParentVisitor.getMsParentMethod(n));
                    msField.setLine(n.getBegin().get().line);
                    msField.setMsId(msId);
                    MsCache.addMsField(msField);
                }
            }
        }
    }

    private static void findEntityField(FieldDeclaration n, MsId msId) {
        EntityFieldBuilder builder = new EntityFieldBuilder();
        builder.find(n, msId);
    }
}



