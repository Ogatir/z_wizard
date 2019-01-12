package z_wizard.gui;

import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZGrabParams;
import z_wizard.containers.ZTagParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ZTagSettings implements ICrossFormable{

    JFrame zTag = new JFrame("Настройки ZTag");
    private JPanel panel1;
    private JTextField sourceFileField;
    private JButton openFileBtn;
    private JTextField portField;
    private JTextField protField;
    private JTextField methodField;
    private JButton saveBtn;
    private JCheckBox logCheck;
    private JCheckBox metaCheck;
    private JCheckBox updatesCheck;
    private JTextField outputDirField;
    private JButton outputBtn;
    private ZTagParams params;

    public ZTagSettings(){
        zTag.setPreferredSize(new Dimension(500,300));
        zTag.add(panel1);
        zTag.setTitle("Настройки ZTag");
        zTag.getContentPane();
        zTag.setLocationRelativeTo(null);
        zTag.pack();

        logCheck.setSelected(true);
        metaCheck.setSelected(true);
        updatesCheck.setSelected(true);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SaveParams(params);
            }
        });
        outputBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser setDir = new JFileChooser();
                setDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                setDir.setCurrentDirectory( new File("/home"));
                int ret = setDir.showDialog(null, "Выбрать папку");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = setDir.getSelectedFile();
                    outputDirField.setText(file.getAbsolutePath());
                }
            }
        });
        openFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                fileopen.setCurrentDirectory( new File("/home"));
                int ret = fileopen.showDialog(null, "Выбрать файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    sourceFileField.setText(file.getAbsolutePath());
                }
            }
        });
    }

    public void SaveParams(AbstractContainer container) {
        Map<String, String> result = new HashMap<String, String>();

        result.put("-i", sourceFileField.getText());
        result.put("-p", portField.getText());
        result.put("-P", protField.getText());
        result.put("-S", methodField.getText());

        result.put("output-file", outputDirField.getText() + "/ztag.json");
        if (logCheck.isSelected())
            result.put("-l", outputDirField.getText() + "/ztag_l.txt");
        else result.put("-l", "/dev/null");
        if (metaCheck.isSelected())
            result.put("-m", outputDirField.getText() + "/ztag_meta.json");
        else result.put("-m", "/dev/null");
        if (updatesCheck.isSelected())
            result.put("--updates-file", outputDirField.getText() + "/ztag_updates.csv");
        else result.put("--updates-file", "/dev/null");

        params.AddZTagParam(result);
    }

    public void Show(AbstractContainer container) {
        params = (ZTagParams) container;
        zTag.setVisible(true);
    }

    public Component[] GetComponents(){
        Component components[] = {sourceFileField, portField, protField, methodField,
                outputDirField, logCheck, metaCheck, updatesCheck};
        return components;
    }

    public void SetParams(ZTagParams params){
        sourceFileField.setText(params.GetZTagParam("-i"));
        portField.setText(params.GetZTagParam("-p"));
        protField.setText(params.GetZTagParam("-P"));
        methodField.setText(params.GetZTagParam("-S"));
        outputDirField.setText(params.GetZTagParam("output-field"));
        logCheck.setSelected(!params.GetZTagParam("-l").equals("/dev/null"));
        metaCheck.setSelected(!params.GetZTagParam("-m").equals("/dev/null"));
        updatesCheck.setSelected(!params.GetZTagParam("--updates-file").equals("/dev/null"));
    }
}
