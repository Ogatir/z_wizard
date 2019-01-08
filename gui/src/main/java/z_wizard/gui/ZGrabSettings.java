package z_wizard.gui;

import z_wizard.containers.AbstractContainer;

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

    public ZGrabSettings(){
        zgrabSet.setPreferredSize(new Dimension(500,300));
        zgrabSet.add(panel1);
        zgrabSet.setTitle("Настройки ZGrab");
        zgrabSet.getContentPane();
        zgrabSet.setLocationRelativeTo(null);
        zgrabSet.pack();
        zgrabSet.setVisible(true);

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
    }

    public void SaveParams(AbstractContainer container) {

    }
}
