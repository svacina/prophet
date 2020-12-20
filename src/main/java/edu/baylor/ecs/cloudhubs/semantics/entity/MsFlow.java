package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

import java.util.List;

@Data
public class MsFlow {
    private String module;
    // controller
    private MsClass controller;
    private MsMethod controllerMethod;
    private List<MsField> controllerServiceFields;
    // service
    private List<MsClass> services;
    private List<MsMethod> serviceMethods;
    private List<MsRestCall> restCall;
    // repository
    private List<MsClass> repositories;
    private List<MsMethod> repositoryMethods;
}
