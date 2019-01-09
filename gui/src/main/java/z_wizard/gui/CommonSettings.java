package z_wizard.gui;

import z_wizard.containers.AbstractContainer;

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

    }

    public void SaveParams(AbstractContainer container) {

    }

    public void Show(AbstractContainer container){
        comm_settings.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
