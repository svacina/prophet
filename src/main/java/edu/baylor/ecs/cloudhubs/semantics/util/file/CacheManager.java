package edu.baylor.ecs.cloudhubs.semantics.util.file;

import edu.baylor.ecs.cloudhubs.semantics.util.MsCache;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class CacheManager {

    public void persistCache(String path){
        writeArrayList("msClassList", MsCache.msClassList, path);
    }

    public <T> void writeArrayList(String name, List<T> list, String path) {
        try {
//            FileOutputStream fos = new FileOutputStream(name + ".txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(path);
//            oos.close();
//            fos.close();
            FileWriter myWriter = new FileWriter("C:\\git\\filename.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }




}
