package z_wizard.gui;

import z_wizard.containers.AbstractContainer;

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
    }

    public void SaveParams(AbstractContainer container) {

    }
    public void Show(AbstractContainer container) {
        zAnnotate.setVisible(true);
    }
}
