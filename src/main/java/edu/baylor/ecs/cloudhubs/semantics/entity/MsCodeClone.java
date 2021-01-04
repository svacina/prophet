package edu.baylor.ecs.cloudhubs.semantics.entity;

import lombok.Data;

@Data
public class MsCodeClone {
    private MsFlowEntity a;
    private MsFlowEntity b;
    private double similarityController;
    private double similarityService;
    private double similarityRepository;
    private double similarityRestCalls;
    private double globalSimilarity;
    private boolean typeA;
    private boolean typeB;

    @Override
    public String toString() {
        return a.toString() + " | " + b.toString() + " : " + globalSimilarity;
    }
}
