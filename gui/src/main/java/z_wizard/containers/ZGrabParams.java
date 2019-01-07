package z_wizard.containers;

public class ZGrabParams extends AbstractContainer{

    public ZGrabParams(String path){
        super(path);
    }

    @Override
    public void Initialize(String keys[], String ... params) {
        for (int i = 0; i < keys.length; i++) {
            if (!params_list.containsKey(keys[i]))
                params_list.put(keys[i], params[i]);
        }
    }

    public void AddZGrabParam(String param_type, String param){
        params_list.put(param_type, param);
    }

    public String GetZGrabParam(String param_name){
        if (params_list.containsKey(param_name))
            return params_list.get(param_name);
        else return null;
    }

}
