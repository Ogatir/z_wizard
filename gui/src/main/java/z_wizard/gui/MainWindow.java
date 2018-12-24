package z_wizard.gui;

import z_wizard.UTIL_TYPE;
import z_wizard.controllers.ExecutionManager;
import z_wizard.containers.ZmapParams;
import z_wizard.gui.DataBaseSettings;
import z_wizard.project.JsonParser;
import z_wizard.project.ProjectParams;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
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
    private JTextArea TextArea;

    private UTIL_TYPE util_type = UTIL_TYPE.UT_INVALID;
    private String progs[] = { "Zmap only" ,"Ztag", "Zgrab" , "Zdns" };
    private String speedTypes[] = {"k", "M"};
    private String zmapKeys[] = {"-B", "-p", "-n", "-T"};
    ExecutionManager executionManager;

    public MainWindow() {
        executionManager = new ExecutionManager();

        this.getContentPane().add(mainPanel);
        progsList.setListData(progs);
        commandField.setEnabled(false);
        String lbl = "<html>" + "Настроить" + "<br>" + "утилиту"+ "</html>";
        utilSetBtn.setText(lbl);

        for (String item : speedTypes)
            speedBox.addItem(item);
        speedBox.setSelectedIndex(1);

        //Open database settings window
        dbSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            DataBaseSettings db = new DataBaseSettings();
            }
        });

        //Open common setting window
        commonSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame db_settings = new JFrame("CommonSettings");
                db_settings.setPreferredSize(new Dimension(300,300));
                db_settings.getContentPane();
                db_settings.setLocationRelativeTo(null);
                db_settings.pack();
                db_settings.setVisible(true);
            }
        });

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
                if (itemEvent.getStateChange()==1) {
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
                    ZmapParams zmapParams = new ZmapParams("zmap");
                    zmapParams.Initialize(zmapKeys, speedParam, portsField.getText(),
                            addrNumberField.getText(), threadsField.getText(), fileNameField.getText());
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
                outputArea.append("Starting:\n");
                if (commandCheckBox.isSelected()){
                    result = executionManager.ExecuteCommand(commandField.getText());
                } else {
                    util_type = GetRequestedUtil(progsList);
                    if (util_type == UTIL_TYPE.UT_INVALID)
                        util_type = UTIL_TYPE.UT_ZMAP_ONLY;
                    Map <String, String> params = new HashMap <String, String>();
                    String zmap_path = "zmap";
                    ZmapParams zmapParams = new ZmapParams(zmap_path);
                    String speedParam = speedField.getText() + speedBox.getItemAt(speedBox.getSelectedIndex());
                    zmapParams.Initialize(zmapKeys, speedParam, portsField.getText(),
                            addrNumberField.getText(), threadsField.getText(), fileNameField.getText());
                    if (toFileCheckBox.isSelected())
                        zmapParams.AddZmapParam("-o", fileNameField.getText());
                    result = executionManager.ExecuteUtils(util_type, zmapParams);
                }
                outputArea.append(result);
                outputArea.append("Finished\n\n");
            }
        });
    }

    public UTIL_TYPE GetRequestedUtil(JList progsList){
        int index = progsList.getSelectedIndex();
        UTIL_TYPE util_type;
        switch (index){
            case 0:
                util_type = UTIL_TYPE.UT_ZMAP_ONLY;
                break;
            default:
                util_type = UTIL_TYPE.UT_INVALID;
                break;
        }
    return util_type;
    }

    public void FillFormWithParams(ProjectParams params){
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

}
