package gui;

import gui.DataBaseSettings;
import gui.containers.ZmapParams;
import gui.controllers.ExecutionManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
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


        dbSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            DataBaseSettings db = new DataBaseSettings();
            }
        });

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
        saveProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Сохранить проект");
                int ret = fileChooser.showSaveDialog(openFileBtn);
                if (ret == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                }
            }
        });
        openProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть проект");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    fileNameField.setText(file.getAbsolutePath());
                }
            }
        });
        newProjectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {



            }
        });
        progsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = progsList.getSelectedIndex();
                switch (index){
                    case 0:
                        util_type = UTIL_TYPE.UT_ZMAP_ONLY;
                        break;
                    case 1:
                        //zmapSet.setVisible(true);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String result;
                outputArea.append("Starting:\n");
                if (commandCheckBox.isSelected()){
                    result = executionManager.ExecuteCommand(commandField.getText());
                } else {
                    Map <String, String> params = new HashMap <String, String>();
                    ZmapParams zmapParams = new ZmapParams();
                    zmapParams.AddZmapParam("-B " ,
                            speedField.getText() + speedBox.getItemAt(speedBox.getSelectedIndex()));
                    zmapParams.AddZmapParam("-p ", portsField.getText());
                    zmapParams.AddZmapParam("-n ", addrNumberField.getText());
                    zmapParams.AddZmapParam("-T ", threadsField.getText());
                    result = executionManager.ExecuteUtils(util_type, zmapParams);
                }
                outputArea.append(result);
                outputArea.append("Finished\n\n");
            }
        });
    }

}
