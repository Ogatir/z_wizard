package z_wizard.executors;

import z_wizard.containers.ZMapParams;
import z_wizard.containers.ZTagParams;

public class ZTagExecutor {

    private String zTagPath;
    private String executionParams;

    public void setzTagPath(String zmapPath) { this.zTagPath = zmapPath; }
    public String getzTagPath() { return zTagPath; }

    public void addExecutionParam(String zTagParams[], ZTagParams params){
        executionParams = zTagPath;
        for (String key : zTagParams){
            String param = params.GetZTagParam(key);
            if (param != null && param.length()!=0){
                executionParams += " " + key;
                executionParams += " " + param;
            }
        }
    }

    public String getExecutionParams() { return executionParams; }
}
