package edu.baylor.ecs.cloudhubs.semantics.service;

import edu.baylor.ecs.cloudhubs.semantics.dto.OpenIdeArgs;

import java.io.*;

public class CommandService {

    public void executeCommands(OpenIdeArgs openIdeArgs) {
        File tempScript = null;
        try {
            tempScript = createTempScript(openIdeArgs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tempScript.delete();
        }
    }

    public File createTempScript(OpenIdeArgs openIdeArgs) throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
                tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        printWriter.println("cd " + openIdeArgs.getIdePath());
        printWriter.println(openIdeArgs.getIde() + " " + openIdeArgs.getFilePath());

        printWriter.close();

        return tempScript;
    }

}

