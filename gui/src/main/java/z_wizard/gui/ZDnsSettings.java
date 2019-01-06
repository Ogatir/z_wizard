package z_wizard.gui;

import javax.swing.*;
import java.awt.*;

public class ZDnsSettings {

    JFrame zdnsSet = new JFrame("Настройки ZDns");
    private JPanel panel1;

    public ZDnsSettings(){
        zdnsSet.setPreferredSize(new Dimension(400,300));
        zdnsSet.add(panel1);
        zdnsSet.setTitle("Настройки ZDns");
        zdnsSet.getContentPane();
        zdnsSet.setLocationRelativeTo(null);
        zdnsSet.pack();
        zdnsSet.setVisible(true);
    }
}
