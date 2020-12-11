package edu.baylor.ecs.cloudhubs.semantics.util.visitor;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsField;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;

public class MsFieldVisitor {

    public static void visitFieldDeclaration(FieldDeclaration n, String path) {
        MsField msField = new MsField();
        if (n.getVariables().size() > 0) {
            VariableDeclarator vd = n.getVariables().get(0);
            String variableName = vd.getNameAsString();
            if (variableName.equals("service") || variableName.equals("repository")) {
                msField.setFieldVariable(vd.getNameAsString());
                if (vd.getType() != null) {
                    msField.setFieldClass(vd.getTypeAsString());
                    msField.setParentMethod(MsParentVisitor.getMsParentMethod(n));
                    msField.setLine(n.getBegin().get().line);
                    msField.setPath(path);
                    MsCache.addMsField(msField);
                }
            }
        }
    }

}



