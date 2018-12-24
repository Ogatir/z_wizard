package z_wizard.containers;

public class ZmapParams extends AbstractContainer {

    private String zmap_path;
    //private String zmapKeys[] = {"-B", "-p", "-n", "-T", "-o"};
    public ZmapParams(String path){
        zmap_path = path;
    }


    @Override
    public void Initialize(String keys[], String ... params) {
        for (int i = 0; i < keys.length; i++) {
            if (!zmap_params_list.containsKey(keys[i]))
                zmap_params_list.put(keys[i], params[i]);
        }
    }

    public void AddZmapParam(String param_type, String param){
            zmap_params_list.put(param_type, param);
    }

    public String GetZmapParam(String param_name){
        if (zmap_params_list.containsKey(param_name))
            return zmap_params_list.get(param_name);
        else return null;
    }

    public String GetZmapPath(){ return zmap_path; }
}
