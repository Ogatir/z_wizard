package z_wizard.project;

import z_wizard.containers.ZMapParams;

public class ProjectParams {

    private String projectName;
    private ZMapParams zmapParams;

    public ProjectParams (String name){
        projectName = name;
    }

    public String getProjectName(){ return projectName; }

    public ZMapParams getZmapParams(){ return zmapParams; }
    public void setZmapParams(ZMapParams zmapParams){ this.zmapParams = zmapParams; }
}
