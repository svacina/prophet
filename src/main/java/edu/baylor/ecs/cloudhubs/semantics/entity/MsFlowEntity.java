package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

import java.util.List;

@Data
public class MsFlowEntity {
    private MsClass msController;
    private MsMethod msControllerMethod;
    private MsMethodCall msServiceMethodCall;
    private MsField msControllerServiceField;
    private MsClass msService;
    private MsMethod msServiceMethod;
    private MsMethodCall msRepositoryMethodCall;
    private MsField msServiceRepositoryField;
    private MsClass msRepository;
    private List<MsRestCall> msRestCalls;
    public MsFlowEntity(MsClass msController, MsMethod msControllerMethod) {
        this.msController = msController;
        this.msControllerMethod = msControllerMethod;
    }

    public MsFlowEntity(MsMethod n) {
        this.msControllerMethod = n;
    }
}
