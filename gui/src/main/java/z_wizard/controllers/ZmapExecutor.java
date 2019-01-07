package z_wizard.controllers;

import z_wizard.containers.ZMapParams;

public class ZmapExecutor {

    private String zmapPath;
    private String executionParams;

    public ZmapExecutor(){ zmapPath = "zmap"; }

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
