package z_wizard.executors;

import z_wizard.UTIL_TYPE;
import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZMapParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutionManager {

    private ZMapExecutor ZMapExecutor;
    private String zmapKeys[] = {"-B", "-p", "-n", "-T", "-o"};
    public ExecutionManager() {
        ZMapExecutor = new ZMapExecutor();
    }

    public String ExecuteUtils(UTIL_TYPE util_type, ZMapParams zmapParams) {
        String execute_params="";
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZMAP_ONLY:
                ZMapExecutor.setZmapPath(zmapParams.GetUtilPath());
                ZMapExecutor.addExecutionParam(zmapKeys, zmapParams);
                execute_params += ZMapExecutor.getExecutionParams();
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

    public String ExecuteUtils(UTIL_TYPE util_type, AbstractContainer params_containers[]) {

        String execute_params = ZMapExecutor.getZmapPath();
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZGRAB:
                break;
            case UT_ZDNS:
                break;
            case UT_ZTAG:
                break;
            case UT_ZANNOTATE:
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