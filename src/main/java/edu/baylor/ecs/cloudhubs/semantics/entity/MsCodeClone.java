package edu.baylor.ecs.cloudhubs.semantics.entity;

import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsFlowEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class MsCodeClone implements Serializable {
    private int id;
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
