package z_wizard.gui;

import javax.swing.*;
import java.awt.*;

public class ZAnnotateSettings {

    JFrame zAnnotate = new JFrame("Настройки ZAnnotate");
    private JPanel panel1;

    public ZAnnotateSettings(){
        zAnnotate.setPreferredSize(new Dimension(400,300));
        zAnnotate.add(panel1);
        zAnnotate.setTitle("Настройки Annotate");
        zAnnotate.getContentPane();
        zAnnotate.setLocationRelativeTo(null);
        zAnnotate.pack();
        zAnnotate.setVisible(true);
    }
}
