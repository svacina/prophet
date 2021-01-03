package edu.baylor.ecs.cloudhubs.semantics.entity.quantification;

import edu.baylor.ecs.cloudhubs.semantics.entity.graph.MsClass;

import java.util.List;

public class ModuleClone {
    private String moduleName;
    private int moduleId;
    private int cfgNr;
    private int percentageClones;
    // all controllers
    private List<MsClass> controllers;
    // cloned controllers
    private List<MsClass> cloneControllers;
}
