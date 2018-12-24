package z_wizard.controllers;

import z_wizard.UTIL_TYPE;
import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZmapParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutionManager {

    private ZmapExecutor zmapExecutor;
    private String zmapKeys[] = {"-B", "-p", "-n", "-T", "-o"};
    public ExecutionManager() {
        zmapExecutor = new ZmapExecutor();
    }

    public String ExecuteUtils(UTIL_TYPE util_type, ZmapParams zmapParams) {

        String execute_params = zmapExecutor.getZmapPath();
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZMAP_ONLY:
                for (String key : zmapKeys){
                    String param = zmapParams.GetZmapParam(key);
                    if (param != null && param.length()!=0){
                        execute_params += " " + key;
                        execute_params += " " + param;
                    }
                }
                break;
        }
        String result = "Executing " + execute_params + "\n";
        try {
            Process proc = Runtime.getRuntime().exec(execute_params);
            result += GetProcOutput(proc);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return result;
    }

    public String ExecuteUtils(UTIL_TYPE util_type, ZmapParams zmapParams, AbstractContainer params_container) {

        String execute_params = zmapExecutor.getZmapPath();
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZMAP_ONLY:;
                for (String key : zmapKeys){
                    String param = zmapParams.GetZmapParam(key);
                    if (param != null){
                        execute_params += " " + key;
                        execute_params += " "+ param;
                    }
                }
                break;
            case   UT_ZGRAB:
                break;
        }
        String result = "Executing " + execute_params + "\n";
        try {
            Process proc = Runtime.getRuntime().exec(execute_params);
            result += GetProcOutput(proc);
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