package edu.baylor.ecs.cloudhubs.semantics.dto;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsCodeClone;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCluster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class AnalyzedCache {
    private List<MsCodeClone> typeA;
    private List<MsCodeClone> typeB;
    private List<EntityCluster> inconsistencies;
}
