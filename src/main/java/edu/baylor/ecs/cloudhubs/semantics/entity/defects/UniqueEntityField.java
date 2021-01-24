package edu.baylor.ecs.cloudhubs.semantics.entity.defects;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
public class UniqueEntityField {
    private String name;
    private String type;
    private HashSet<String> annotationsHashSet;
    private List<String> annotationList;

    public UniqueEntityField(String name, String type){
        this.name = name;
        this.type = type;
        annotationsHashSet = new HashSet<>();
        annotationList = new ArrayList<>();
    }

    public void addAnnotation(String string) {
        this.annotationsHashSet.add(string);
    }

    public void addAll(List<String> annotations) {
        this.annotationsHashSet.addAll(annotations);
    }

    public void convertHashSet(){
        this.annotationList = new ArrayList<String>(annotationsHashSet);
    }
}
