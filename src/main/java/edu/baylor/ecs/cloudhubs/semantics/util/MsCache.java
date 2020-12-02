package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsMethod;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsMethodCall;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsRestCall;
import edu.baylor.ecs.cloudhubs.semantics.entity.inconsistencies.MsInconsistencies;

import java.util.ArrayList;
import java.util.List;

public class MsCache {
    private static List<MsClass> msClassList;
    private static List<MsMethod> msMethodList;
    private static List<MsMethodCall> msMethodCallList;
    private static List<MsRestCall> msRestCallList;
    private static MsInconsistencies msInconsistencies;

    public static void init(){
        msClassList = new ArrayList<>();
        msMethodList = new ArrayList<>();
        msMethodCallList = new ArrayList<>();
        msInconsistencies = new MsInconsistencies();
        msRestCallList = new ArrayList<>();
        msInconsistencies = new MsInconsistencies();
    }

    public static void addMsClass(MsClass msClass) {
        msClassList.add(msClass);
    }

    public static void addMsMethod(MsMethod msMethod) {
        msMethodList.add(msMethod);
    }

    public static void addMsMethodCall(MsMethodCall msMethodCall) {
        msMethodCallList.add(msMethodCall);
    }

    public static void print(){
        msClassList.forEach(n -> System.out.println(n.toString()));
    }

    public static void addMsRestMethodCall(MsRestCall msRestCall){
        msRestCallList.add(msRestCall);
    }

}
