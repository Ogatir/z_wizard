package z_wizard.containers;

import java.util.Map;

public class DataBaseParams extends AbstractContainer {

    public DataBaseParams(){

    }

    @Override
    public void Initialize(String keys[], String ... params) {
        for (int i = 0; i < keys.length; i++) {
            if (!params_list.containsKey(keys[i]))
                params_list.put(keys[i], params[i]);
        }
    }

    public void AddDBParam(String param_type, String param){
        params_list.put(param_type, param);
    }
    public void AddDBParam(Map<String, String> newParams){
        params_list.putAll(newParams);
    }

    public String GetDBParam(String param_name){
        if (params_list.containsKey(param_name))
            return params_list.get(param_name);
        else return null;
    }
}
