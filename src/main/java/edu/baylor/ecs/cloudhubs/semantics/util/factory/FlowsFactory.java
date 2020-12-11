package edu.baylor.ecs.cloudhubs.semantics.util.factory;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsClassRoles;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsMethodCall;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 1. get controllers associated with the module
 * 2. create flows per each controller
 * 3. per each controller find method calls
 * 4. per each method call find ms field
 * 5. ms field get type of the service
 * 6. find the service in the methods
 * 7. for each service find method calls
 * 8. for each service find rest calls -> add to the flow
 * 9. for each service method call find the field
 * 10. for each field find
 */
public class FlowsFactory {

    public static void createFlows(){

        for (String module: MsCache.modules
             ) {
            //1. get controllers associated with the module
            List<MsClass> controllers = MsCache.msClassList
                    .stream()
                    .filter(n -> n.getRole().equals(MsClassRoles.CONTROLLER) && n.getPath().contains(module))
                    .collect(Collectors.toList());
            for (MsClass controller: controllers
                 ) {
                System.out.println(controller.toString());
            }
        }

        // for each controller


//        controllers.forEach(c -> {
//            // find method calls
//            List<MsMethodCall> serviceMethodCalls = MsCache.msMethodCallList
//                    .stream()
//                    .filter(mc -> c.getClassId().equals(mc.getParentClassId()))
//                    .collect(Collectors.toList());
//            // msMethodCall.getCalledServiceId() msMethodCall.getCalledClassId
//        });
        // find services

        // find method calls from the service

        // find repositories based on mc


    }
}
