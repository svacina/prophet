package edu.baylor.ecs.cloudhubs.examiner.data;

import lombok.Data;

@Data
public class CmdArgs {
    private String sut;
    private String data;
    private boolean analyzeInconsistency;
    private boolean analyzeCodeClone;
}
