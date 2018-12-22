package gui.controllers;

import gui.UTIL_TYPE;
import gui.containers.AbstractContainer;
import gui.containers.ZmapParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

public class ExecutionManager {

    private ZmapExecutor zmapExecutor;
    private String zmapKeys[] = {"-B", "-p", "-n", "-T"};
    public ExecutionManager() {
        zmapExecutor = new ZmapExecutor();
    }

    public String ExecuteUtils(UTIL_TYPE util_type, AbstractContainer params_container) {

        String execute_params = zmapExecutor.getZmapPath();
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZMAP_ONLY:
                ZmapParams zmapParams = (ZmapParams) params_container;
                for (String key : zmapKeys){
                    String param = zmapParams.GetZmapParam(key);
                    if (param != null){
                        execute_params += key;
                        execute_params += param;
                    }
                }
                break;
            case   UT_ZGRAB:
                break;
        }

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

    public String ExecuteCommand(String command){
        String result = "";
        try {
            Process proc = Runtime.getRuntime().exec(command);
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

        BufferedReader err_reader =
                new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        line = "";
        while ( (line = err_reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

}