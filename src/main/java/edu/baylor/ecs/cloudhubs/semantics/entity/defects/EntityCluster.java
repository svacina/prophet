package edu.baylor.ecs.cloudhubs.semantics.entity.defects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EntityCluster {
    private String name;
    private List<MsEntityClass> msEntities;
    private List<UniqueEntityField> uniqueFields;
    private boolean hasMissingFiledAnnotations;

    public EntityCluster(String name){
        msEntities = new ArrayList<>();
        this.name = name;
    }

    public void addEntity(MsEntityClass msEntityClass) {
        msEntities.add(msEntityClass);
    }
}
