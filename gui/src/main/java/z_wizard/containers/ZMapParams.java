package z_wizard.containers;

import java.util.HashMap;
import java.util.Map;

public class ZMapParams extends AbstractContainer {

    public ZMapParams(String path){
          super(path);
      }

    @Override
    public void Initialize(String keys[], String ... params) {
        for (int i = 0; i < keys.length; i++) {
            if (!params_list.containsKey(keys[i]))
                params_list.put(keys[i], params[i]);
        }
    }

    public void AddZmapParam(String param_type, String param){
        params_list.put(param_type, param);
    }
    public void AddZmapParam(Map<String, String> newParams){
        params_list.putAll(newParams);
    }

    public String GetZmapParam(String param_name){
        if (params_list.containsKey(param_name))
            return params_list.get(param_name);
        else return null;
    }
}
