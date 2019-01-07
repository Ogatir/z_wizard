package z_wizard.executors;

import z_wizard.containers.ZDnsParams;

public class ZDnsExecutor {

    private String zDnsPath;
    private String executionParams;

    public void setzDnsPath(String zmapPath) { this.zDnsPath = zmapPath; }
    public String getzDnsPath() { return zDnsPath; }

    public void addExecutionParam(String zmapParams[], ZDnsParams params){
        executionParams = zDnsPath;
        for (String key : zmapParams){
            String param = params.GetZDnsParam(key);
            if (param != null && param.length()!=0){
                executionParams += " " + key;
                executionParams += " " + param;
            }
        }
    }

    public String getExecutionParams() { return executionParams; }
}
