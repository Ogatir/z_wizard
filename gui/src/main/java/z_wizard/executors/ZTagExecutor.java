package z_wizard.executors;

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
                if (key.equals("output-file"))
                    continue;
                executionParams += " " + key;
                if (key.equals("-l"))
                    if (param.equals("/dev/null"))
                        executionParams += " " + "/dev/null";
                    else
                        executionParams += " " + param;
                else if (key.equals("-m"))
                    if (param.equals("/dev/null"))
                        executionParams += " " + "/dev/null";
                    else
                        executionParams += " " + param;
                else if (key.equals("--updates-file"))
                    if (param.equals("/dev/null"))
                        executionParams += " " + "/dev/null";
                    else
                        executionParams += " " + param;
                else
                    executionParams += " " + param;
            }
        }
        //executionParams += " > " + params.GetZTagParam("output-file") + "/ztag.json";
    }

    public String getExecutionParams() { return executionParams; }
}
