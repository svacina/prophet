package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCluster;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.UniqueEntityField;
import java.util.*;

public class EntityClusterManager {

    public void generateDefects(){
        clusterEntities();
        getUniqueFields();
        yieldMissingAnnotationsPerField();
        yieldMissingFieldsPer();
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

    public void yieldMissingAnnotationsPerField(){
        EntityCache.entityClusterList.forEach(n -> {
            n.getMsEntities().forEach(m -> {
                m.getFields().forEach(f -> {
                    Optional<UniqueEntityField> op = n.getUniqueFields()
                            .stream()
                            .filter(u -> u.getType().equals(f.getType()) && u.getName().equals(f.getName()))
                            .findFirst();
                    if (op.isPresent()) {
                        UniqueEntityField uf = op.get();
                        f.setMissingAnnotations(new ArrayList<>(com.google.common.collect.Sets.difference(uf.getAnnotationsHashSet(), new HashSet<String>(f.getAnnotations()))));
                    }
                });
            });
        });
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

    private void yieldMissingFieldsPer() {
        EntityCache.entityClusterList.forEach(n -> {
            n.getMsEntities().forEach(m -> {
                m.getFields().forEach(f -> {
                    n.getUniqueFields().forEach(u -> {
                        if (!(u.getName().equals(f.getName()) && u.getType().equals(f.getType()))){
//                            System.out.println("missing field");
                        }
                    });
                });
            });
        });
    }
}
