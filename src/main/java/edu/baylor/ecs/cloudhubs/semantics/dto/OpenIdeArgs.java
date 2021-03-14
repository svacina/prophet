package edu.baylor.ecs.cloudhubs.semantics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class OpenIdeArgs {
    private String ide;
    private String idePath;
    private String filePath;
}