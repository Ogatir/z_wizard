package z_wizard.gui;

import z_wizard.UTIL_TYPE;
import z_wizard.containers.ZMapOutputParams;
import z_wizard.executors.ExecutionManager;
import z_wizard.containers.ZMapParams;
import z_wizard.project.JsonParser;
import z_wizard.project.ProjectParams;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField fileNameField;
    private JTextArea outputArea;
    private JButton saveProjectBtn;
    private JButton dbSetBtn;
    private JButton startBtn;
    private JPanel toolBarPanel;
    private JPanel settingsPanel;
    private JPanel outputPanel;
    private JList progsList;
    private JPanel listPanel;
    private JPanel bottomPanel;
    private JButton commonSetBtn;
    private JCheckBox toFileCheckBox;
    private JButton openFileBtn;
    private JButton openProjectBtn;
    private JCheckBox commandCheckBox;
    private JTextField commandField;
    private JButton newProjectBtn;
    private JButton utilSetBtn;
    private JPanel zmapSet;
    private JTextField portsField;
    private JTextField addrNumberField;
    private JTextField speedField;
    private JComboBox speedBox;
    private JTextField threadsField;
    private JButton outputFieldsBtn;
    private JCheckBox outputFieldsCheck;
    private JTextArea TextArea;


    private String progs[] = { "ZMap only" ,"ZGrab", "ZDns", "ZTag", "ZAnnotate" };
    private String speedTypes[] = {"k", "M"};
    private String zmapKeys[] = {"-B", "-p", "-n", "-T"};
    private UTIL_TYPE util_type = UTIL_TYPE.UT_INVALID;
    private ZGrabSettings zGrabSettings;
    private ZDnsSettings zdns;
    private ZTagSettings ztag;
    private ZAnnotateSettings zAnnotate;
    private DataBaseSettings db;
    private ZMapOutputs zMapOutputs;
    private ExecutionManager executionManager;

    private ZMapOutputParams zMapOutputParams;
    public MainWindow() {
        executionManager = new ExecutionManager();
        this.getContentPane().add(mainPanel);
        progsList.setListData(progs);
        commandField.setEnabled(false);
        outputFieldsBtn.setEnabled(false);
        String lbl = "<html>" + "Настроить" + "<br>" + "утилиту"+ "</html>";
        utilSetBtn.setText(lbl);
        for (String item : speedTypes)
            speedBox.addItem(item);
        speedBox.setSelectedIndex(0);
        //Open database settings window
        dbSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            DataBaseSettings db = new DataBaseSettings();
            }
        });
        //Open common setting window
        commonSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) { CommonSettings comm_settings = new CommonSettings(); }});

        //Open file
        openFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    fileNameField.setText(file.getAbsolutePath());
                }
            }
        });

        //Set/unset command
        commandCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange()== ItemEvent.SELECTED) {
                    commandField.setEnabled(true);
                    zmapSet.setEnabled(false);
                }
                else {
                    commandField.setEnabled(false);
                    zmapSet.setEnabled(true);
                }
            }
        });

        //Save project
        saveProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Сохранить проект");
                int ret = fileChooser.showSaveDialog(openFileBtn);
                if (ret == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    ProjectParams project = new ProjectParams("Test project v0.1");
                    String speedParam = speedField.getText() + speedBox.getItemAt(speedBox.getSelectedIndex());
                    ZMapParams zmapParams = new ZMapParams("zmap");
                    zmapParams.Initialize(zmapKeys, speedParam, portsField.getText(),
                            addrNumberField.getText(), threadsField.getText());
                    zmapParams.AddZmapParam("-o", fileNameField.getText());
                    project.setZmapParams(zmapParams);
                    JsonParser.ProjectToJson(project, file);
                }
            }
        });

        //Open project
        openProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть проект");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    fileNameField.setText(file.getAbsolutePath());
                    ProjectParams params = JsonParser.JsonToProject(file);
                    FillFormWithParams(params);
                }
            }
        });

        //New project
        newProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String result;

                if (commandCheckBox.isSelected()){
                    result = executionManager.ExecuteCommand(commandField.getText());
                    outputArea.append(result);
                    outputArea.append("Finished\n\n");
                    return;
                }
                util_type = GetRequestedUtil(progsList);
                if (util_type == UTIL_TYPE.UT_INVALID)
                    util_type = UTIL_TYPE.UT_ZMAP_ONLY;
                if (CheckParams(util_type, outputArea))
                    return;
                String zmap_path = "zmap";
                ZMapParams zmapParams = new ZMapParams(zmap_path);
                outputArea.append("Starting:\n");
                String speedParam = "";
                if (!speedField.getText().equals(""))
                    speedParam = speedField.getText() + speedBox.getItemAt(speedBox.getSelectedIndex());
                zmapParams.Initialize(zmapKeys, speedParam, portsField.getText(),
                        addrNumberField.getText(), threadsField.getText());
                if (toFileCheckBox.isSelected())
                    zmapParams.AddZmapParam("-o", fileNameField.getText());
                if (outputFieldsCheck.isSelected())
                    zmapParams.AddZmapParam(zMapOutputParams.GetZmapOutputParamMap());
                result = executionManager.ExecuteUtils(util_type, zmapParams);
                outputArea.append(result);
                outputArea.append("Finished\n\n");
            }
        });

        utilSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                switch (GetRequestedUtil(progsList)){
                    case UT_ZGRAB:
                        zGrabSettings = new ZGrabSettings();
                        break;
                    case UT_ZDNS:
                        zdns = new ZDnsSettings();
                        break;
                    case UT_ZTAG:
                        ztag = new ZTagSettings();
                        break;
                    case UT_ZANNOTATE:
                        zAnnotate = new ZAnnotateSettings();
                        break;
                    default:
                        break;
                }
            }
        });
        outputFieldsCheck.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange()== ItemEvent.SELECTED)
                    outputFieldsBtn.setEnabled(true);
                else
                    outputFieldsBtn.setEnabled(false);
            }
        });

        outputFieldsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                zMapOutputParams = new ZMapOutputParams();
                zMapOutputs = new ZMapOutputs(zMapOutputParams);
               // zMapOutputs.SaveParams(zMapOutputParams);
            }
        });
    }

    private UTIL_TYPE GetRequestedUtil(JList progsList){
        int index = progsList.getSelectedIndex();
        UTIL_TYPE util_type;
        switch (index){
            case 0:
                util_type = UTIL_TYPE.UT_ZMAP_ONLY;
                break;
            case 1:
                util_type = UTIL_TYPE.UT_ZGRAB;
                break;
            case 2:
                util_type = UTIL_TYPE.UT_ZDNS;
                break;
            case 3:
                util_type = UTIL_TYPE.UT_ZTAG;
                break;
            case 4:
                util_type = UTIL_TYPE.UT_ZANNOTATE;
                break;
            default:
                util_type = UTIL_TYPE.UT_INVALID;
                break;
        }
    return util_type;
    }

    private void FillFormWithParams(ProjectParams params){
        String zmapKeys[] = {"-B", "-p", "-n", "-T", "-o"};
        List<String> zmapParams = new LinkedList<String>();
        for (String key : zmapKeys){
            String param = params.getZmapParams().GetZmapParam(key);
            zmapParams.add(param);
        }
        if (zmapParams.get(0).contains("M"))
            speedBox.setSelectedIndex(1);
        else if (zmapParams.get(0).contains("k"))
            speedBox.setSelectedIndex(0);
        String str  = zmapParams.get(0).substring(0,zmapParams.get(0).length() - 1);
        speedField.setText(str);
        portsField.setText(zmapParams.get(1));
        addrNumberField.setText(zmapParams.get(2));
        threadsField.setText(zmapParams.get(3));
        fileNameField.setText(zmapParams.get(4));
    }

    private boolean CheckParams(UTIL_TYPE type, JTextArea outputArea){

        String errorMessage="";
        boolean hasError = false;
        switch (type){
            case UT_ZMAP_ONLY:
                if (portsField.getText().equals("")){
                    errorMessage = "ОШИБКА: Введите номера портов для сканирования\n";
                    hasError = true;
                }
                break;
            case UT_ZGRAB:
                break;
            case UT_ZDNS:
                break;
            case UT_ZTAG:
                break;
            case UT_ZANNOTATE:
                break;
            default:
                break;
        }
        if (hasError)
            outputArea.append(errorMessage);
        return hasError;
    }

}
