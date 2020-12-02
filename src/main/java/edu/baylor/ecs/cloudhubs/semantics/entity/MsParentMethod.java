package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsParentMethod {
    private String methodName;
    private String className;
    private String packageName;
}
