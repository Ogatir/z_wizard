import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

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
    private JTextArea TextArea;

    private String progs[] = { "ztag", "zgrab" , "zdns" };

    public MainWindow() {
        this.getContentPane().add(mainPanel);
        progsList.setListData(progs);
        commandField.setEnabled(false);

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
                if (itemEvent.getStateChange()==1)
                    commandField.setEnabled(true);
                else commandField.setEnabled(false);
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
    }

}
