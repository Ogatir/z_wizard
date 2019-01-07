package z_wizard.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ZAnnotateSettings implements ICrossFormable{

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

    public HashMap<String, String> GetParams() {
        return null;
    }

}
