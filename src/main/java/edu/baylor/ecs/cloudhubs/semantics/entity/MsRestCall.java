package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MsRestCall extends MsMethodCall{
    private String api;
    private String httpMethod;
    private String returnType;
}
