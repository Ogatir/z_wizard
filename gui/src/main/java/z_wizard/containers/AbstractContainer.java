package gui.containers;

import java.util.List;
import java.util.Map;

public abstract class AbstractContainer {
    Map <String, String> zmap_params_list;
    Map <String, String> util_params_list;

    public  AbstractContainer(){

    }

    public void AddZmapParam(String param_type, String param){
        if (!param.equals("")) {
            zmap_params_list.put(param_type, param);
        }
    }

    public String GetZmapParam(String param_name){
        if (zmap_params_list.containsKey(param_name))
            return zmap_params_list.get(param_name);
        else return null;
    }
}
