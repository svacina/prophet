package edu.baylor.ecs.cloudhubs.semantics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AnalyzeSutArgs {
    protected String sutRoot;
    protected String dataOutputPath;
    protected boolean logResults;
    protected boolean logStats;
}
