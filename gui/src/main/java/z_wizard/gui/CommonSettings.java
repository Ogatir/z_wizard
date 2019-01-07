package z_wizard.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CommonSettings implements ICrossFormable {
    private JPanel panel1;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField zmapPathField;
    private JTextField ZGrabPathField;
    private JTextField ZDnsPathField;
    private JTextField ZtagPathField;
    private JTextField ZAnnotPathField;
    JFrame comm_settings= new JFrame("Настройки");

    public CommonSettings(){
        comm_settings.setPreferredSize(new Dimension(400,300));
        comm_settings.add(panel1);
        comm_settings.setTitle("Настройки");
        comm_settings.getContentPane();
        comm_settings.setLocationRelativeTo(null);
        comm_settings.pack();
        comm_settings.setVisible(true);
    }

    public HashMap<String, String> GetParams() {
        return null;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
