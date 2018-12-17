package controllers;

public class ZmapExecutor {

    private String zmapPath;
    private String progParams;

    public ZmapExecutor(){
        zmapPath = "zmap";
    }

    public void setZmapPath(String zmapPath) { this.zmapPath = zmapPath; }
    public String getZmapPath() { return zmapPath; }
}
