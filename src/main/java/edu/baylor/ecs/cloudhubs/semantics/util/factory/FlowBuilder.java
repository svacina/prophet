package edu.baylor.ecs.cloudhubs.semantics.util.factory;

import edu.baylor.ecs.cloudhubs.semantics.entity.*;
import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlowBuilder {

    public List<MsFlowEntity> buildFlows(){
        List<MsFlowEntity> msFlowEntities = findControllerMethods();
        for (MsFlowEntity msFlowEntity: msFlowEntities
             ) {
            // 1. get controller
            msFlowEntity.setMsController(findController(msFlowEntity));
            // 2. get service method call in controller method
            Optional<MsMethodCall> serviceMethodCall = findServiceCall(msFlowEntity.getMsControllerMethod());
            if (serviceMethodCall.isPresent()) {
                msFlowEntity.setMsServiceMethodCall(serviceMethodCall.get());
                // 3. get service field variable in controller class by method call
                Optional<MsField> serviceField = findServiceField(msFlowEntity.getMsServiceMethodCall());
                if (serviceField.isPresent()) {

                    System.out.println();
                    msFlowEntity.setMsControllerServiceField(serviceField.get());
                    // 4. get service class
                    Optional<MsClass> msServiceClass = findService(msFlowEntity.getMsControllerServiceField());
                    if (msServiceClass.isPresent()) {
                        msFlowEntity.setMsService(msServiceClass.get());
                        // 5. find service method name
                        Optional<MsMethod> msServiceMethod = findMsServiceMethod(msFlowEntity.getMsService(), msFlowEntity.getMsServiceMethodCall());
                        if (msServiceMethod.isPresent()) {
                            msFlowEntity.setMsServiceMethod(msServiceMethod.get());
                            // 5. get rest calls
                            List<MsRestCall> restCalls = findRestCalls(msFlowEntity.getMsServiceMethod());
                            msFlowEntity.setMsRestCalls(restCalls);
                        }
                    }
                }
            }
        }
        return msFlowEntities;
    }

    private Optional<MsMethod> findMsServiceMethod(MsClass msService, MsMethodCall controllerServiceMethodCall) {
        return MsCache.msMethodList.stream()
                .filter(n -> n.getMsId().getPath().equals(msService.getMsId().getPath()) && n.getMethodName().equals(controllerServiceMethodCall.getCalledMethodName()))
                .findFirst();
    }

    private List<MsRestCall> findRestCalls(MsMethod msMethodService) {
        return MsCache.msRestCallList.stream()
                .filter(n -> n.getParentClassName().equals(msMethodService.getClassName()) && n.getParentMethodName().equals(msMethodService.getMethodName()))
                .collect(Collectors.toList());
    }

    private Optional<MsClass> findService(MsField msControllerServiceField) {
        return MsCache.msClassList.stream()
                .filter(n -> n.getClassName().equals(msControllerServiceField.getFieldClass() + "Impl"))
                .findFirst();
    }

    private Optional<MsField> findServiceField(MsMethodCall msMethodCall) {
        return MsCache.msFieldList.stream()
                .filter(n -> n.getMsId().getPath().equals(msMethodCall.getMsId().getPath()) && n.getFieldVariable().equals(msMethodCall.getCalledServiceId()))
                .findFirst();
    }

    public List<MsFlowEntity> findControllerMethods()  {
        return MsCache.msMethodList
                .stream()
                .filter(n -> n.getMsId().getPath().toLowerCase().contains(MsClassRoles.CONTROLLER.toString().toLowerCase()))
                .map(MsFlowEntity::new)
                .collect(Collectors.toList());
    }

    public MsClass findController(MsFlowEntity msFlowEntity) {
        return MsCache.msClassList
                .stream()
                .filter(n -> n.getMsId().getPath().equals(msFlowEntity.getMsControllerMethod().getMsId().getPath()))
                .findFirst()
                .get();
    }

    private Optional<MsMethodCall> findServiceCall(MsMethod msControllerMethod) {
        return MsCache.msMethodCallList
                .stream()
                .filter(n -> n.getParentMethodName().equals(msControllerMethod.getMethodName()) && n.getMsId().getPath().equals(msControllerMethod.getMsId().getPath()))
                .findFirst();
    }


}
