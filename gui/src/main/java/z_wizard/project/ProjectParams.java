package z_wizard.project;

import z_wizard.containers.ZmapParams;

public class ProjectParams {

    private String projectName;
    private ZmapParams zmapParams;

    public ProjectParams (String name){
        projectName = name;
    }

    public String getProjectName(){ return projectName; }

    public ZmapParams getZmapParams(){ return zmapParams; }
    public void setZmapParams(ZmapParams zmapParams){ this.zmapParams = zmapParams; }
}
