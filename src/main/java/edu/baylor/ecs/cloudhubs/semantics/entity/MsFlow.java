package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

import java.util.List;

@Data
public class MsFlow {
    private MsClass controller;
    private MsClass service;
    private MsClass repository;
    private List<MsRestCall> restCall;
    private List<MsMethodCall> msMethodCalls;
}
