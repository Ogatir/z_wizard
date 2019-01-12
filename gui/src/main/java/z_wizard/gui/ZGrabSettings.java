package z_wizard.gui;

import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZGrabParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ZGrabSettings implements ICrossFormable{

    JFrame zgrabSet = new JFrame("Настройки ZGrab");
    private JPanel panel1;
    private JTextField portField;
    private JTextField fileNameField;
    private JButton selectFileBtn;
    private JTextField sourceFileField;
    private JButton sourceFileBtn;
    private JTextArea addParamsArea;
    private JButton saveBtn;

    private ZGrabParams params;

    public ZGrabSettings(){
        zgrabSet.setPreferredSize(new Dimension(500,300));
        zgrabSet.add(panel1);
        zgrabSet.setTitle("Настройки ZGrab");
        zgrabSet.getContentPane();
        zgrabSet.setLocationRelativeTo(null);
        zgrabSet.pack();

        selectFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Выбрать файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    fileNameField.setText(file.getAbsolutePath());
                }
            }
        });
        sourceFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Выбрать файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    sourceFileField.setText(file.getAbsolutePath());
                }
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SaveParams(params);
            }
        });
    }

    public void SaveParams(AbstractContainer container) {
        Map <String, String> result = new HashMap<String, String>();

        result.put("--port", portField.getText());
        if (!addParamsArea.getText().equals(""))
            result.put("add-params", addParamsArea.getText());
        result.put("--input-file", sourceFileField.getText());
        result.put("--output-file", fileNameField.getText());
        params.AddZGrabParam(result);
    }

    public Component[] GetComponents(){
        Component components[] = {portField, fileNameField, sourceFileField, addParamsArea};
        return components;
    }

    public void SetParams(ZGrabParams params){
        portField.setText(params.GetZGrabParam("--port"));
        addParamsArea.setText(params.GetZGrabParam("add-params"));
        fileNameField.setText(params.GetZGrabParam("--output-file"));
        sourceFileField.setText(params.GetZGrabParam("--input-file"));
    }

    public void Show(AbstractContainer container) {
        params = (ZGrabParams) container;
        zgrabSet.setVisible(true);
    }
}
