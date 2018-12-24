package z_wizard.gui;

import javax.swing.*;
import java.awt.*;

public class CommonSettings {
    JFrame comm_settings = new JFrame("Настройки");
    private JPanel panel1;

    public CommonSettings(){
        comm_settings.setPreferredSize(new Dimension(400,300));
        comm_settings.add(panel1);
        comm_settings.setTitle("Настройки");
        comm_settings.getContentPane();
        comm_settings.setLocationRelativeTo(null);
        comm_settings.pack();
        comm_settings.setVisible(true);
    }
}
