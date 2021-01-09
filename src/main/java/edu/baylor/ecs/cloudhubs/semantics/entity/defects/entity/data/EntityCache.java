package edu.baylor.ecs.cloudhubs.semantics.entity.defects.entity.data;

import edu.baylor.ecs.cloudhubs.semantics.entity.defects.entity.model.MsEntityField;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.entity.model.MsEntityClass;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class EntityCache {
    public static List<MsEntityField> entityFields;
    public static List<MsEntityClass> entityClasses;
    public static void init(){
        entityClasses = new ArrayList<>();
        entityFields = new ArrayList<>();
    }

    public static void addEntityField(MsEntityField msEntityField) {
        Optional<MsEntityClass> msEntityClass = entityClasses.stream().filter(n -> n.getPath().equals(msEntityField.getPath())).findFirst();
        msEntityClass.ifPresent(entityClass -> entityClass.addField(msEntityField));
        entityFields.add(msEntityField);
    }
}
