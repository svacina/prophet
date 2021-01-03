package edu.baylor.ecs.cloudhubs.semantics.util.factory;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsCodeClone;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsFlowEntity;
import edu.baylor.ecs.cloudhubs.semantics.entity.quantification.ModuleClonePair;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleClonePairFactory {

    private CodeClonesFactory codeClonesFactory;

    public ModuleClonePairFactory(){
        codeClonesFactory = new CodeClonesFactory();
    }

//    private double

    /**
     * compare ever module with the other
     * look at how similar those two modules are
     */
    public void createModuleClonePairs() {
        for (int i = 0; i < MsCache.modules.size(); i++) {
            for (int j = 0; j < MsCache.modules.size(); j++) {
                String iModule = MsCache.modules.get(i);
                String jModule = MsCache.modules.get(j);
                if (!iModule.equals(jModule)) {
                    ModuleClonePair moduleClonePair = new ModuleClonePair();
                    moduleClonePair.setModuleA(iModule);
                    moduleClonePair.setModuleB(jModule);
                    moduleClonePair.setModuleAFlows(codeClonesFactory.getFlowEntities(iModule));
                    moduleClonePair.setModuleBFlows(codeClonesFactory.getFlowEntities(jModule));
                    moduleClonePair.setCodeClones(getAssociatedCodeClones(iModule, jModule));
                    moduleClonePair.setPercentClonesModuleA(flowRepresentation(moduleClonePair.getModuleAFlows(), moduleClonePair.getCodeClones()));
                }
            }
        }
    }


    /**
     * Find code clones such that A has moduleA and B has moduleB
     */
    private List<MsCodeClone> getAssociatedCodeClones(String moduleA, String moduleB){
        return MsCache.msCodeClones.stream()
                .filter(n ->
                        (n.getA().getMsController().getMsId().getPath().contains(moduleA) && n.getB().getMsController().getMsId().getPath().contains(moduleB) && n.isTypeA() && n.isTypeB()) ||
                        (n.getA().getMsController().getMsId().getPath().contains(moduleB) && n.getB().getMsController().getMsId().getPath().contains(moduleA) && n.isTypeA() && n.isTypeB())
                        )
                .collect(Collectors.toList());
    }


    /**
     * How many Flows are represented in each module
     */
    private double flowRepresentation(List<MsFlowEntity> flowEntities, List<MsCodeClone> codeClones) {
        // iterate through module A flows in code clones and look if it is present in the code clones (a)
        int same = 0;
        for (MsFlowEntity msFlowEntity: flowEntities
             ) {
            for (MsCodeClone msCodeClone: codeClones
                 ) {
                if (msFlowEntity
                        .getMsController()
                        .getMsId().getPath()
                        .contains(msCodeClone.getA().getMsController().getMsId().getPath()) ||
                        msFlowEntity.getMsController().getMsId().getPath().contains(msCodeClone.getB().getMsController().getMsId().getPath())
                ){
                    same += 1;
                    break;
                }
            }
        }
        if (flowEntities.size() == 0) {
            return 0.0;
        } else {
            return (same / flowEntities.size()) * 100;
        }

    }
}
