package z_wizard.executors;

import z_wizard.containers.ZMapParams;

import java.util.HashMap;

public class ZMapExecutor {

    private String zmapPath;
    private String executionParams;

    public ZMapExecutor(){ zmapPath = "zmap"; }

    public void setZmapPath(String zmapPath) { this.zmapPath = zmapPath; }
    public String getZmapPath() { return zmapPath; }

    public void addExecutionParam(String zmapParams[], ZMapParams params){
        executionParams = zmapPath;
        for (String key : zmapParams){
            String param = params.GetZmapParam(key);
            if (param != null && param.length()!=0){
                executionParams += " " + key;
                executionParams += " " + param;
            }
        }
    }

    public String getExecutionParams() { return executionParams; }
}
