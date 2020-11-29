package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.MsClass;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsMethod;
import edu.baylor.ecs.cloudhubs.semantics.entity.MsMethodCall;

import java.util.ArrayList;
import java.util.List;

public class MsCache {
    private static List<MsClass> msClassList;
    private static List<MsMethod> msMethodList;
    private static List<MsMethodCall> msMethodCallList;

    public static void init(){
        msClassList = new ArrayList<>();
        msMethodList = new ArrayList<>();
        msMethodCallList = new ArrayList<>();
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
}
