package z_wizard;

import z_wizard.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String args[]){
        System.out.println("Z-wizard begins his magic");

        MainWindow main_window = new MainWindow();
        main_window.pack();
        main_window.setSize(new Dimension(900,500));
        main_window.setLocationRelativeTo(null);
        main_window.setVisible(true);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
