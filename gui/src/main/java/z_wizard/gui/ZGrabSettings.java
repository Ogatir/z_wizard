package z_wizard.gui;

import javax.swing.*;
import java.awt.*;

public class ZGrabSettings {

    JFrame zgrabSet = new JFrame("Настройки ZGrab");
    private JPanel panel1;

    public ZGrabSettings(){
        zgrabSet.setPreferredSize(new Dimension(400,300));
        zgrabSet.add(panel1);
        zgrabSet.setTitle("Настройки ZGrab");
        zgrabSet.getContentPane();
        zgrabSet.setLocationRelativeTo(null);
        zgrabSet.pack();
        zgrabSet.setVisible(true);
    }
}
