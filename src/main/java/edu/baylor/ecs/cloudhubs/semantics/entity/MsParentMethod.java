package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MsParentMethod {
    private String parentMethodName;
    private String parentClassName;
    private String parentPackageName;
}
