package edu.baylor.ecs.cloudhubs.semantics.util.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.baylor.ecs.cloudhubs.semantics.entity.*;
import edu.baylor.ecs.cloudhubs.semantics.entity.defects.EntityCache;
import edu.baylor.ecs.cloudhubs.semantics.entity.graph.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CacheService {

    private String path = "C:\\git\\data\\";

    public void persistCache(String path){
        this.path = path;
        writeArrayList("msClassList", MsCacheService.msClassList);
        writeArrayList("msMethodList", MsCacheService.msMethodList);
        writeArrayList("msMethodCallList", MsCacheService.msMethodCallList);
        writeArrayList("msRestCallList", MsCacheService.msRestCallList);
        writeArrayList("msFieldList", MsCacheService.msFieldList);
        writeArrayList("msModulesList", MsCacheService.modules);
        writeArrayList("msFlowList", MsCacheService.msFlows);
        writeArrayList("msCodeClones", MsCacheService.msCodeClones);
        writeArrayList("msCodeClonesTypeA", MsCacheService.typeA);
        writeArrayList("msCodeClonesTypeB", MsCacheService.typeB);
    }

    public void persistInconsistencies(String path) {
        this.path = path;
        writeArrayList("entityClusterList", EntityCache.entityClusterList);
    }

    public void persistTimes(String path, List<String> times) {
        this.path = path;
        writeArrayList("times", times);
    }

    public void persistStats(String path, String stats) {
        this.path = path;
        try (FileWriter writer = new FileWriter(path +"/stats.json");
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(stats);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public <T> void writeArrayList(String name, List<T> list) {
        try (FileWriter writer = new FileWriter(path +"/" + name + ".json");
             BufferedWriter bw = new BufferedWriter(writer)) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(list);
                bw.write(jsonString);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void recreateCache(String cachePath){

        this.path = cachePath;

        Gson gson = new Gson();

        String data = readDataIntoString("msModulesList");
        Type listOfMyClassObject = new TypeToken<ArrayList<String>>() {}.getType();
        List<String> msModulesList = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.modules = msModulesList;

        data = readDataIntoString("msClassList");
        listOfMyClassObject = new TypeToken<ArrayList<MsClass>>() {}.getType();
        List<MsClass> msClassList = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msClassList = msClassList;

        data = readDataIntoString("msMethodList");
        listOfMyClassObject = new TypeToken<ArrayList<MsMethod>>() {}.getType();
        List<MsMethod> msMethodList = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msMethodList = msMethodList;

        data = readDataIntoString("msMethodCallList");
        listOfMyClassObject = new TypeToken<ArrayList<MsMethodCall>>() {}.getType();
        List<MsMethodCall> msMethodCallList = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msMethodCallList = msMethodCallList;

        data = readDataIntoString("msRestCallList");
        listOfMyClassObject = new TypeToken<ArrayList<MsRestCall>>() {}.getType();
        List<MsRestCall> msRestCallList = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msRestCallList = msRestCallList;

        data = readDataIntoString("msFieldList");
        listOfMyClassObject = new TypeToken<ArrayList<MsField>>() {}.getType();
        List<MsField> msFieldList = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msFieldList = msFieldList;

        data = readDataIntoString("msFlowList");
        listOfMyClassObject = new TypeToken<ArrayList<MsFlowEntity>>() {}.getType();
        List<MsFlowEntity> msFlowEntities = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msFlows = msFlowEntities;

        data = readDataIntoString("msCodeClones");
        listOfMyClassObject = new TypeToken<ArrayList<MsFlowEntity>>() {}.getType();
        List<MsCodeClone> msCodeClones = gson.fromJson(data, listOfMyClassObject);
        MsCacheService.msCodeClones = msCodeClones;
    }

    public String readDataIntoString(String name) {
        String s = null;
        try {
            s = Files.readString(Paths.get(path + name + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
