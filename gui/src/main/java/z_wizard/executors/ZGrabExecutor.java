package z_wizard.executors;

import z_wizard.containers.ZGrabParams;

public class ZGrabExecutor {

    private String zGrabPath;
    private String executionParams;

    public void setzGrabPath(String zmapPath) { this.zGrabPath = zmapPath; }
    public String getzGrabPath() { return zGrabPath; }

    public void addExecutionParam(String zGrabParams[], ZGrabParams params){
        executionParams = zGrabPath;
        for (String key : zGrabParams){
            String param = params.GetZGrabParam(key);
            if (param != null && param.length()!=0){
                if (key.equals("add-params")){
                    executionParams += " " + param;
                } else if (key.equals("--input-file") || key.equals("--output-file")){
                    executionParams += " " + key;
                    executionParams += "=" + param;
                } else {
                    executionParams += " " + key;
                    executionParams += " " + param;
                }


            }
        }
    }

    public String getExecutionParams() { return executionParams; }
}
