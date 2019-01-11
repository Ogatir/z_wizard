package z_wizard.project;

import z_wizard.containers.CommonSettingsParams;
import z_wizard.containers.ZDnsParams;
import z_wizard.containers.ZMapParams;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ProjectSerializer {

//    private Component[] mainWindowSettings;
//    private Component[] commonSettingsWindow;
//    private Component[] zDnsWindow;
//    private Component[] zGrabWindow;
//    private Component[] zTagWindow;
//    private Component[] zAnnotateWindow;

    private ProjectParams projectParams;

    public ProjectSerializer(String projectName){
        projectParams = new ProjectParams(projectName);
    }

    public void SerializeZMapParams(JTextField speedField, JTextField portsField, JTextField addrNumberField,
                                    JTextField threadsField, JTextField fileNameField, JComboBox speedBox, String pathToZmap){
        String zmapKeys[] = {"-B", "-p", "-n", "-T", };
        ZMapParams zMapParams = new ZMapParams(pathToZmap);
        String speedParam =  speedField.getText() + speedBox.getItemAt(speedBox.getSelectedIndex());

        zMapParams.Initialize(zmapKeys, speedParam, portsField.getText(),
                addrNumberField.getText(), threadsField.getText());
        zMapParams.AddZmapParam("-o", fileNameField.getText());
        projectParams.setZmapParams(zMapParams);
    }

    public void SerializeCommonParams(Component components[]){
        CommonSettingsParams commonSettingsParams = new CommonSettingsParams();
        String keys[] = {"zmapPathField", "ZGrabPathField", "ZDnsPathField", "ZtagPathField", "ZAnnotPathField"};
        JTextField zmapPathField = (JTextField) components[0];
        JTextField ZGrabPathField = (JTextField) components[1];
        JTextField ZDnsPathField = (JTextField) components[2];
        JTextField ZtagPathField = (JTextField) components[3];
        JTextField ZAnnotPathField = (JTextField) components[4];
        commonSettingsParams.Initialize(keys, zmapPathField.getText(), ZGrabPathField.getText(),
                ZDnsPathField.getText(), ZtagPathField.getText(), ZAnnotPathField.getText());
        projectParams.setCommonSettingsParams(commonSettingsParams);
    }

    public void SerializeZDnsParams(Component components[],String pathToZDns){
        ZDnsParams zDnsParams = new ZDnsParams(pathToZDns);
        String keys[] = {"moduleField", "addrField", "fileNameField"};
        JComboBox moduleField = (JComboBox) components[0];
        JTextField addrField = (JTextField) components[1];
        JTextField fileNameField = (JTextField) components[2];
        zDnsParams.Initialize(keys, moduleField.getItemAt(moduleField.getSelectedIndex()).toString(), addrField.getText(), fileNameField.getText());
        projectParams.setzDnsParams(zDnsParams);
    }

    public ProjectParams getProjectParams() {
        return projectParams;
    }

    private Map<String, Component>  createComponentMap(JPanel panel) {
        Map<String, Component> componentMap = new HashMap<String,Component>();
        Component[] components = panel.getComponents();
        for (int i=0; i < components.length; i++) {
            String name = components[i].getName();
            componentMap.put(components[i].getName(), components[i]);
        }
        return componentMap;
    }

    private Component getComponentByName(String name, Map<String, Component> componentMap) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        }
        else return null;
    }


}
