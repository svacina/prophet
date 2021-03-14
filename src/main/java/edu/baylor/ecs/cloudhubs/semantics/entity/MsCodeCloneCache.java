package edu.baylor.ecs.cloudhubs.semantics.entity;

import java.util.ArrayList;
import java.util.List;

public class MsCodeCloneCache {
    private List<MsCodeClone> highSimilar;

    public MsCodeCloneCache(){
        highSimilar = new ArrayList<>();
    }

    public void addHighSimilar(MsCodeClone mcc) {
        highSimilar.add(mcc);
    }
}
