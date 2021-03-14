package edu.baylor.ecs.cloudhubs.semantics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AnalyzeTime extends AnalyzeSutArgs{
    private String testOutputPath;
}
