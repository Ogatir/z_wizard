package z_wizard.gui;

import javax.swing.*;
import java.awt.*;

public class ZTagSettings {

    JFrame zTag = new JFrame("Настройки ZTag");
    private JPanel panel1;

    public ZTagSettings(){
        zTag.setPreferredSize(new Dimension(400,300));
        zTag.add(panel1);
        zTag.setTitle("Настройки ZTag");
        zTag.getContentPane();
        zTag.setLocationRelativeTo(null);
        zTag.pack();
        zTag.setVisible(true);
    }
}
