package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MsField extends MsParentMethod {
    private String fieldClass;
    private String fieldVariable;
    private MsParentMethod parentMethod;
    private int line;

    @Override
    public String toString() {
        return "[L" + line + "] " +
                parentMethod.getParentPackageName() + '.' +
                parentMethod.getParentClassName() + '.' +
                parentMethod.getParentMethodName() +
                " : " +
                fieldClass + '.' +
                fieldVariable + '.'
                ;
    }
}
