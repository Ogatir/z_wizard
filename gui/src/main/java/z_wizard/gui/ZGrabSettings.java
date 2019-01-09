package z_wizard.gui;

import z_wizard.containers.AbstractContainer;
import z_wizard.containers.ZDnsParams;
import z_wizard.containers.ZGrabParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

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
    }

    public void SaveParams(AbstractContainer container) {

    }

    public void Show(AbstractContainer container) {
        params = (ZGrabParams) container;
        zgrabSet.setVisible(true);
    }
}
