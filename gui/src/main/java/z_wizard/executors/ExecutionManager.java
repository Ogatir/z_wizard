package z_wizard.executors;

import z_wizard.UTIL_TYPE;
import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZDnsParams;
import z_wizard.containers.ZGrabParams;
import z_wizard.containers.ZMapParams;

import java.io.*;

public class ExecutionManager {

    private ZMapExecutor zMapExecutor;
    private ZDnsExecutor zDnsExecutor;
    private ZGrabExecutor zGrabExecutor;
    private String zmapKeys[] = {"-B", "-p", "-n", "-T", "-o", "-f", "--output-fields"};
    private String zDnsKeys[] = {"page", "module", "--output-file"};
    private String zGrabKeys[] = {"--port", "add-params", "--input-file", "--output-file"};

    public ExecutionManager() {
        zMapExecutor = new ZMapExecutor();
        zDnsExecutor = new ZDnsExecutor();
        zGrabExecutor = new ZGrabExecutor();
    }

    public String ExecuteUtils(UTIL_TYPE util_type, ZMapParams zmapParams) {
        String execute_params="";
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZMAP_ONLY:
                zMapExecutor.setZmapPath(zmapParams.GetUtilPath());
                zMapExecutor.addExecutionParam(zmapKeys, zmapParams);
                execute_params += zMapExecutor.getExecutionParams();
                break;
        }

        String result = "Executing " + execute_params + "\n";
        try {
            Process proc = Runtime.getRuntime().exec(execute_params);
            result += GetProcOutput(proc);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
            result += e.getMessage() + '\n';
        }
        return result;
    }

    public String ExecuteUtils(UTIL_TYPE util_type, AbstractContainer paramContainers[]) {

        String execute_params ="";
        switch (util_type){
            case UT_INVALID:
                break;
            case UT_ZGRAB:
                ZGrabParams zGrabParams = (ZGrabParams) paramContainers[0];
                zGrabExecutor.setzGrabPath((zGrabParams.GetUtilPath()));
                zGrabExecutor.addExecutionParam(zGrabKeys, zGrabParams);
                execute_params += zGrabExecutor.getExecutionParams();
                break;
            case UT_ZDNS:
                ZDnsParams zDnsParams = (ZDnsParams) paramContainers[0];
                zDnsExecutor.setzDnsPath(zDnsParams.GetUtilPath());
                zDnsExecutor.addExecutionParam(zDnsKeys, zDnsParams);
                execute_params += zDnsExecutor.getExecutionParams();
                break;
            case UT_ZTAG:
                break;
            case UT_ZANNOTATE:
                break;
        }
        String result = "Executing " + execute_params + "\n";
        try {
            if (util_type == UTIL_TYPE.UT_ZDNS){
                Process proc = Runtime.getRuntime().exec(execute_params);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(proc.getOutputStream()));
                writer.write(zDnsExecutor.getResourse());
                writer.close();
                result += GetProcOutput(proc);
                return result;
            }
            Process proc = Runtime.getRuntime().exec(execute_params);
            result += GetProcOutput(proc);
        } catch (IOException e) {
            e.printStackTrace();
            result += e.getMessage() + '\n';
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
            result += e.getMessage() + '\n';
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