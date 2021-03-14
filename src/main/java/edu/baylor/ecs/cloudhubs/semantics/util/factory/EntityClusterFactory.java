package edu.baylor.ecs.cloudhubs.semantics.util.factory;

import edu.baylor.ecs.cloudhubs.semantics.entity.defects.*;

import java.util.*;

public class EntityClusterFactory {

    public void generateDefects(){
        clusterEntities();
        getUniqueFields();
        yieldMissingAnnotationsPerField();
        yieldMissingFieldsPer();
        yieldMissingFields();
        yieldAnnotationsWithoutEntity();
        listInconsistencies();
    }

    private void listInconsistencies() {
        EntityCache.entityClusterList.forEach(n -> {
            if (n.isHasMissingFiledAnnotations()) {
                n.getMsEntities().forEach(e -> {
                    if (e.isHasMissingFiledAnnotations() || e.isHasMissingField()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(e.getPath());
                        e.getMissingFields().forEach(m -> {
//                            System.out.println(e.getPath() + " " + m.getType() + " " + m.getName());
                        });
                        e.getFields().forEach( f -> {
                            f.getMissingAnnotations().forEach( m -> {
//                                System.out.println(e.getPath() + " " + f.getType() + " " + f.getName() + " " + m);
                            });
                        });
                    }
                });
            }
        });
    }

    public void yieldAnnotationsWithoutEntity(){
//        System.out.println("Data validation present");
        int counter = 0;
        EntityCache.entityClusterList.forEach(n -> {
            n.getMsEntities().forEach(e -> {

                if (e.getDocument() == null) {
                    e.getFields().forEach(f -> {
                        if (f.getAnnotations() != null && !f.getAnnotations().isEmpty()) {
                            e.setHasValidationAnnotations(true);
                        }
                    });
                    if (e.isHasValidationAnnotations()) {
//                        System.out.println(e.getPath() + " " + e.getDocument() + " " + e.getData());
                    }
                }
            });
        });
        for (EntityCluster ec: EntityCache.entityClusterList) {
            for (MsEntityClass mec: ec.getMsEntities()) {
                for (MsEntityField mef: mec.getFields()) {
                    counter += 1;
                }
            }
        }
//        System.out.println(counter);
//        System.out.println("-----------");
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

    public void yieldMissingFields(){
        EntityCache.entityClusterList.forEach(n -> {

            n.getUniqueFields().forEach(u -> {
                n.getMsEntities().forEach(m -> {
                    // find in unique fields those guys that
                    Optional<MsEntityField> op = m.getFields()
                            .stream()
                            .filter(b -> b.getType().equals(u.getType()) && b.getName().equals(u.getName()))
                            .findFirst();
                    if (op.isEmpty()) {
                        m.addMissingField(u);
                        m.setHasMissingField(true);
                    }
                });
            });


        });
    }

    public void yieldMissingAnnotationsPerField(){
        EntityCache.entityClusterList.forEach(n -> {
            n.getMsEntities().forEach(m -> {
                m.getFields().forEach(f -> {
                    if (m.getDocument() != null && !(m.getDocument().equals(""))) {
                        Optional<UniqueEntityField> op = n.getUniqueFields()
                                .stream()
                                .filter(u -> u.getType().equals(f.getType()) && u.getName().equals(f.getName()))
                                .findFirst();
                        if (op.isPresent()) {
                            UniqueEntityField uf = op.get();
                            f.setMissingAnnotations(new ArrayList<>(com.google.common.collect.Sets.difference(uf.getAnnotationsHashSet(), new HashSet<String>(f.getAnnotations()))));
                            if (f.getMissingAnnotations().size() > 0) {
                                m.setHasMissingFiledAnnotations(true);
                                n.setHasMissingFiledAnnotations(true);
                            }
                        }
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
