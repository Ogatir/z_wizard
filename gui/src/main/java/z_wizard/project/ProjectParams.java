package z_wizard.project;

import z_wizard.containers.*;

public class ProjectParams {

    private String projectName;
    private ZMapParams zmapParams;
    private ZMapOutputParams zMapOutputParams;
    private ZGrabParams zGrabParams;
    private ZDnsParams zDnsParams;
    private ZTagParams zTagParams;
    private ZAnnotateParams zAnnotateParams;

    private CommonSettingsParams commonSettingsParams;

    public ProjectParams (String name){
        projectName = name;
    }

    public String getProjectName(){ return projectName; }

    public ZMapParams getZmapParams(){ return zmapParams; }
    public void setZmapParams(ZMapParams zmapParams){ this.zmapParams = zmapParams; }

    public CommonSettingsParams getCommonSettingsParams() {
        return commonSettingsParams;
    }
    public void setCommonSettingsParams(CommonSettingsParams commonSettingsParams) {
        this.commonSettingsParams = commonSettingsParams;
    }

    public ZMapOutputParams getzMapOutputParams() {
        return zMapOutputParams;
    }
    public void setzMapOutputParams(ZMapOutputParams zMapOutputParams) {
        this.zMapOutputParams = zMapOutputParams;
    }

    public ZDnsParams getzDnsParams() {
        return zDnsParams;
    }
    public void setzDnsParams(ZDnsParams zDnsParams) {
        this.zDnsParams = zDnsParams;
    }

    public ZGrabParams getzGrabParams() {
        return zGrabParams;
    }
    public void setzGrabParams(ZGrabParams zGrabParams) {
        this.zGrabParams = zGrabParams;
    }

    public ZTagParams getzTagParams() {
        return zTagParams;
    }
    public void setzTagParams(ZTagParams zTagParams) {
        this.zTagParams = zTagParams;
    }

    public ZAnnotateParams getzAnnotateParams() {
        return zAnnotateParams;
    }
    public void setzAnnotateParams(ZAnnotateParams zAnnotateParams) {
        this.zAnnotateParams = zAnnotateParams;
    }
}
