package z_wizard.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

public class ZDnsSettings implements ICrossFormable{

    JFrame zdnsSet = new JFrame("Настройки ZDns");
    private JPanel panel1;
    private JTextField recordTypeField;
    private JTextField addrField;
    private JTextField fileNameField;
    private JButton saveFileBtn;

    public ZDnsSettings(){
        zdnsSet.setPreferredSize(new Dimension(400,300));
        zdnsSet.add(panel1);
        zdnsSet.setTitle("Настройки ZDns");
        zdnsSet.getContentPane();
        zdnsSet.setLocationRelativeTo(null);
        zdnsSet.pack();
        zdnsSet.setVisible(true);

        saveFileBtn.addActionListener(new ActionListener() {
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

    public HashMap<String, String> GetParams() {
        return null;
    }

}
