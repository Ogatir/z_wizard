package z_wizard.executors;

import z_wizard.containers.ZDnsParams;

public class ZDnsExecutor {

    private String zDnsPath;
    private String executionParams;

    public void setzDnsPath(String zmapPath) { this.zDnsPath = zmapPath; }
    public String getzDnsPath() { return zDnsPath; }

    public void addExecutionParam(String zDnsParams[], ZDnsParams params){
        executionParams = "echo ";
        executionParams += params.GetZDnsParam(zDnsParams[0]) + " | ";
        executionParams += zDnsPath;
        executionParams += " " + params.GetZDnsParam(zDnsParams[1]);
        executionParams += " " + zDnsParams[2];
        executionParams += " " + params.GetZDnsParam(zDnsParams[2]);
//        for (String key : zDnsParams){
//            String param = params.GetZDnsParam(key);
//            if (param != null && param.length()!=0){
//                if (key.charAt(0) == '-')
//                    executionParams += " " + key;
//                executionParams += " " + param;
//            }
//        }
    }

    public String getExecutionParams() { return executionParams; }
}
