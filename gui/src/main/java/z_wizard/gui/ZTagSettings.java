package z_wizard.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ZTagSettings implements ICrossFormable{

    JFrame zTag = new JFrame("Настройки ZTag");
    private JPanel panel1;
    private JTextField textField1;
    private JButton openFileBtn;
    private JTextField textField2;
    private JComboBox comboBox1;

    public ZTagSettings(){
        zTag.setPreferredSize(new Dimension(500,300));
        zTag.add(panel1);
        zTag.setTitle("Настройки ZTag");
        zTag.getContentPane();
        zTag.setLocationRelativeTo(null);
        zTag.pack();
        zTag.setVisible(true);
    }

    public HashMap<String, String> GetParams() {
        return null;
    }

}
