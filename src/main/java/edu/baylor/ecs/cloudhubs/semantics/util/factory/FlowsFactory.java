package edu.baylor.ecs.cloudhubs.semantics.util.factory;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsClassRoles;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsMethodCall;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;

import java.util.List;
import java.util.stream.Collectors;

public class FlowsFactory {

    public static void createFlows(){
        // for each controller
        List<MsClass> controllers = MsCache.msClassList
                .stream()
                .filter(n -> n.getRole().equals(MsClassRoles.CONTROLLER))
                .collect(Collectors.toList());

        controllers.forEach(c -> {
            // find method calls
            List<MsMethodCall> serviceMethodCalls = MsCache.msMethodCallList
                    .stream()
                    .filter(mc -> c.getClassId().equals(mc.getParentClassId()))
                    .collect(Collectors.toList());
            // msMethodCall.getCalledServiceId() msMethodCall.getCalledClassId
        });
        // find services

        // find method calls from the service

        // find repositories based on mc


    }
}
