package edu.baylor.ecs.cloudhubs.semantics.entity.graph;

import lombok.Data;

@Data
public class MsField extends MsParentMethod {
    private MsId msId;
    private String code;
    private String fieldClass;
    private String fieldVariable;
    private MsParentMethod parentMethod;
    private int line;
}
