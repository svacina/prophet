package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCluster;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityDefect;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.UniqueEntityField;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityClusterManager {

    public void generateDefects(){
        clusterEntities();
        getUniqueFields();
        yieldDefects();
    }

    public void clusterEntities(){
        EntityCache.entityClasses.forEach(n -> {
            Optional<EntityCluster> o = EntityCache.entityClusterList.stream().filter(e -> e.getName().equals(n.getName())).findFirst();
            if (o.isPresent()) {
                o.get().addEntity(n);
            } else {
                EntityCluster ec = new EntityCluster(n.getName());
                ec.addEntity(n);
                EntityCache.entityClusterList.add(ec);
            }
        });
    }

    public void getUniqueFields(){
        EntityCache.entityClusterList.forEach(n -> {
            List<UniqueEntityField> uniqueFields = getUniqueEntityFields(n);
            n.setUniqueFields(uniqueFields);
        });
    }

    public void yieldDefects(){
//        EntityCache.entityClusterList.forEach(n -> {
//            List<EntityDefect> defects = getDefects(n);
//            n.setDefects(defects);
//        });
    }

    /* Compare unique fields */
    /* Compare annotations per field */
    private List<EntityDefect> getDefects(EntityCluster entityCluster) {
        /* for each unique field look at every class and every field in that class
        * and compare the fields and annotations in that class with unique one*/
        entityCluster.getUniqueFields().forEach(uniqueEntityField -> {
            List<EntityDefect> defects = new ArrayList<>();
            entityCluster.getMsEntities().forEach(msEntityClass -> {
                EntityDefect defect = new EntityDefect();
                msEntityClass.getFields().forEach(msEntityField -> {
                    // here compare those two

                });
            });
        });
        return null;
    }


    private List<UniqueEntityField> getUniqueEntityFields(EntityCluster entityCluster) {
        List<UniqueEntityField> uef = new ArrayList<>();
        entityCluster.getMsEntities().forEach(e -> {
            e.getFields().forEach(f -> {
                if (f.getType() != null) {
                    Optional<UniqueEntityField> op = uef
                            .stream()
                            .filter(n -> n.getName().equals(f.getName()) && n.getType().equals(f.getType()))
                            .findFirst();
                    if (op.isEmpty()){
                        UniqueEntityField uniqueEntityField = new UniqueEntityField(f.getName(), f.getType());
                        uniqueEntityField.addAll(f.getAnnotations());
                        uef.add(uniqueEntityField);
                        // add annotations as well from f
                    } else {
                        UniqueEntityField uniqueEntityField = op.get();
                        uniqueEntityField.addAll(f.getAnnotations());
                    }
                }
            });
        });
        return uef;
    }

}
