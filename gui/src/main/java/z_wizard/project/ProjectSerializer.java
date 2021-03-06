package z_wizard.project;

import z_wizard.containers.*;

import javax.swing.*;
import java.awt.*;

public class ProjectSerializer {

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

    public void SerializeZGrabParams(Component components[], String pathToZGrab){
        ZGrabParams zGrabParams = new ZGrabParams(pathToZGrab);
        String keys[] = {"--port", "--output-file", "--input-file", "add-params"};
        JTextField portField = (JTextField) components[0];
        JTextField fileNameField = (JTextField) components[1];
        JTextField sourceFileField = (JTextField) components[2];
        JTextArea addParamsArea = (JTextArea) components[3];
        zGrabParams.Initialize(keys, portField.getText(), fileNameField.getText(), sourceFileField.getText(), addParamsArea.getText());
        projectParams.setzGrabParams(zGrabParams);
    }
    public void SerializeZTagParams(Component components[], String pathToZGTag){
        ZTagParams zTagParams = new ZTagParams(pathToZGTag);
        String zTagKeys[] = {"-i", "-p", "-P", "-S", "output-file", "-l", "-m", "--updates-file"};
        JTextField sourceFileField = (JTextField) components[0];
        JTextField portField = (JTextField) components[1];
        JTextField protField = (JTextField) components[2];
        JTextField methodField = (JTextField) components[3];
        JTextField outputDirField = (JTextField) components[4];
        JCheckBox logCheck = (JCheckBox) components[5];
        JCheckBox metaCheck = (JCheckBox) components[6];
        JCheckBox updatesCheck = (JCheckBox) components[7];

        zTagParams.Initialize(zTagKeys, sourceFileField.getText(), portField.getText(), protField.getText(),
                methodField.getText(), outputDirField.getText(),
                logCheck.isEnabled() ? outputDirField.getText() + "/ztag_log.txt" :"/dev/null",
                metaCheck.isEnabled() ? outputDirField.getText() + "/ztag_meta.json" :"/dev/null",
                updatesCheck.isEnabled() ? outputDirField.getText() + "/ztag_u.csv" :"/dev/null");
        projectParams.setzTagParams(zTagParams);
    }
    public void SerializeDBParams(Component components[]){
        DataBaseParams dataBaseParams = new DataBaseParams();
//        String DatabaseKeys[]
//        result.put("server", serverField.getText());
//        result.put("login", loginField.getText());
//        result.put("pass", passField.getText());

    }

    public ProjectParams getProjectParams() {
        return projectParams;
    }

}
