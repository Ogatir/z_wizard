package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutionManager {


    private ZmapExecutor zmapExecutor;

    public ExecutionManager() {
        zmapExecutor = new ZmapExecutor();
    }

    public String ExecuteUtils() {

        String result = "";
        try {
            Process proc = Runtime.getRuntime().exec(zmapExecutor.getZmapPath());
            result = GetProcOutput(proc);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return result;
    }

    private String GetProcOutput(Process proc) throws IOException{
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

}