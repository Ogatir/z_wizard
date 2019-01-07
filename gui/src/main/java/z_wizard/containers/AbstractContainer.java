package z_wizard.containers;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractContainer {
    Map <String, String> params_list;
    private String utilPath;

    public  AbstractContainer(String path){
        params_list = new HashMap<String, String>();
        utilPath = path;
    }

    public void Initialize(String keys[], String ... params ){

    }

    public String GetUtilPath(){
        return utilPath;
    }

}
