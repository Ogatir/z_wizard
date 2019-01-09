package z_wizard.executors;

import z_wizard.containers.ZDnsParams;

public class ZDnsExecutor {

    private String zDnsPath;
    private String executionParams;
    private String resourse;

    public void setzDnsPath(String zmapPath) { this.zDnsPath = zmapPath; }
    public String getzDnsPath() { return zDnsPath; }

    public void addExecutionParam(String zDnsParams[], ZDnsParams params){
        resourse = params.GetZDnsParam(zDnsParams[0]);
        executionParams = "";
        executionParams += zDnsPath;
        executionParams += " " + params.GetZDnsParam(zDnsParams[1]);
        executionParams += " " + zDnsParams[2];
        executionParams += " " + params.GetZDnsParam(zDnsParams[2]);
    }

    public String getResourse(){
        return resourse;
    }
    public String getExecutionParams() { return executionParams; }
}
