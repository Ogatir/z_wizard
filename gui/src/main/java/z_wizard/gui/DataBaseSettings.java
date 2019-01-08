package z_wizard.gui;

import z_wizard.containers.AbstractContainer;

import javax.swing.*;
import java.awt.*;

public class DataBaseSettings implements ICrossFormable{
    private JPanel panel1;
    private JButton saveBtn;
    private JButton cancelBtn;
    private JPasswordField passwordField1;
    private JFormattedTextField formattedTextField1;
    private JTextField textField1;
    private JButton connectToButton;
    JFrame db_settings = new JFrame("Database Settings");

    public DataBaseSettings(){
        db_settings.setPreferredSize(new Dimension(400,300));
        db_settings.add(panel1);
        db_settings.setTitle("Соединение с базой данных");
        db_settings.getContentPane();
        db_settings.setLocationRelativeTo(null);
        db_settings.pack();
        db_settings.setVisible(true);
    }

    public void SaveParams(AbstractContainer container) {

    }
}
