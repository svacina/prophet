package edu.baylor.ecs.cloudhubs.semantics.entity.defects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MsEntityClass {
    private String path;
    private String name;
    private String code;
    private boolean isEnum;
    private String data;
    private String document;
    private String collectionName;
    private List<MsEntityField> fields;
    private List<UniqueEntityField> missingFields;
    private boolean hasMissingFiledAnnotations;
    private boolean hasMissingField;
    private boolean hasValidationAnnotations;

    public MsEntityClass() {
        fields = new ArrayList<>();
        missingFields = new ArrayList<>();
    }

    public void addMissingField(UniqueEntityField uniqueEntityField) {
        missingFields.add(uniqueEntityField);
    }

    public void addField(MsEntityField msEntityField) {
        fields.add(msEntityField);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append(" ");
        sb.append(data);
        sb.append(" ");
        sb.append(document);
        sb.append(" ");
        sb.append(collectionName);
        return sb.toString();
    }
}
