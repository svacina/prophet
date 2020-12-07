package edu.baylor.ecs.cloudhubs.semantics.util;

import edu.baylor.ecs.cloudhubs.semantics.entity.*;
import edu.baylor.ecs.cloudhubs.semantics.entity.inconsistencies.MsInconsistencies;

import java.util.ArrayList;
import java.util.List;

public class MsCache {
    public static List<MsClass> msClassList;
    public static List<MsMethod> msMethodList;
    public static List<MsMethodCall> msMethodCallList;
    public static List<MsRestCall> msRestCallList;
    public static List<MsField> msFieldList;
    public static MsInconsistencies msInconsistencies;

    public static void init(){
        msClassList = new ArrayList<>();
        msMethodList = new ArrayList<>();
        msMethodCallList = new ArrayList<>();
        msInconsistencies = new MsInconsistencies();
        msRestCallList = new ArrayList<>();
        msFieldList = new ArrayList<>();
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

    public static void addMsField(MsField msField) {
        msFieldList.add(msField);
    }

    public static void addMsRestMethodCall(MsRestCall msRestCall){
        msRestCallList.add(msRestCall);
    }

    public static void print(){
        System.out.printf("");
        System.out.println();
    }

}
