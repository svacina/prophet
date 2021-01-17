package edu.baylor.ecs.cloudhubs.semantics.entity.defects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MsEntityField {
    private String name;
    private String type;
    private String path;
    private String code;
    private String lineNumber;
    private List<String> annotations;
    private List<String> missingAnnotations;

    public MsEntityField(){
        annotations = new ArrayList<>();
        missingAnnotations = new ArrayList<>();
    }

    public void addAnnotation(String s) {
        annotations.add(s);
    }

    public void addMissingAnnotation(String s) { missingAnnotations.add(s); }
}
