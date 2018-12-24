package z_wizard.containers;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractContainer {
    Map <String, String> zmap_params_list;
    Map <String, String> util_params_list;

    public  AbstractContainer(){
        zmap_params_list = new HashMap<String, String>();
        util_params_list = new HashMap<String, String>();
    }

    public void Initialize(String keys[], String ... params ){

    }

}
