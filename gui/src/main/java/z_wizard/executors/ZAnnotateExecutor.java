package z_wizard.executors;

import z_wizard.containers.ZAnnotateParams;

public class ZAnnotateExecutor {

    private String zAnnotatePath;
    private String executionParams;

    public void setzTagPath(String zmapPath) { this.zAnnotatePath = zmapPath; }
    public String getzTagPath() { return zAnnotatePath; }

    public void addExecutionParam(String zAnnotateParams[], ZAnnotateParams params){
        executionParams = zAnnotatePath;
        for (String key : zAnnotateParams){
            String param = params.GetZAnnotateParam(key);
            if (param != null && param.length()!=0){
                executionParams += " " + key;
                executionParams += " " + param;
            }
        }
    }

    public String getExecutionParams() { return executionParams; }
}
