package edu.baylor.ecs.cloudhubs.semantics.entity.defects.entity.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MsEntityField {
    private String path;
    private String code;
    private String lineNumber;
    private List<String> annotations;

    public MsEntityField(){
        annotations = new ArrayList<>();
    }

    public void addAnnotation(String s) {
        annotations.add(s);
    }
}
