package z_wizard.executors;

import z_wizard.containers.ZGrabParams;
import z_wizard.containers.ZMapParams;

public class ZGrabExecutor {

    private String zGrabPath;
    private String executionParams;

    public void setzGrabPath(String zmapPath) { this.zGrabPath = zmapPath; }
    public String getzGrabPath() { return zGrabPath; }

    public void addExecutionParam(String zmapParams[], ZGrabParams params){
        executionParams = zGrabPath;
        for (String key : zmapParams){
            String param = params.GetZGrabParam(key);
            if (param != null && param.length()!=0){
                executionParams += " " + key;
                executionParams += " " + param;
            }
        }
    }

    public String getExecutionParams() { return executionParams; }
}
