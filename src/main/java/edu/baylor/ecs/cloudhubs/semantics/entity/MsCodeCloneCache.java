package edu.baylor.ecs.cloudhubs.semantics.entity;

import org.checkerframework.checker.units.qual.A;

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
