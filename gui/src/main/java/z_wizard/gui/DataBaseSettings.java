package z_wizard.gui;

import z_wizard.DataBase;
import z_wizard.containers.AbstractContainer;
import z_wizard.containers.DataBaseParams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataBaseSettings implements ICrossFormable{
    private JPanel panel1;
    private JButton saveBtn;
    private JButton cancelBtn;
    private JPasswordField passField;
    private JFormattedTextField loginField;
    private JTextField serverField;
    private JButton connectToButton;
    private JLabel connStatus;
    private JFrame db_settings = new JFrame("Database Settings");
    private DataBaseParams params;
    DataBase db;

    public DataBaseSettings(DataBase db){
        db_settings.setPreferredSize(new Dimension(400,300));
        db_settings.add(panel1);
        db_settings.setTitle("Соединение с базой данных");
        db_settings.getContentPane();
        db_settings.setLocationRelativeTo(null);
        db_settings.pack();
        InitForm();
        this.db = db;
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SaveParams(params);
            }
        });
        connectToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Properties properties = new Properties();
                    properties.setProperty("user", loginField.getText());
                    properties.setProperty("password", passField.getText());
                    properties.setProperty("useUnicode", "true");
                    properties.setProperty("characterEncoding", "utf8");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://"+serverField.getText()+"/zwizard" +
                            "?useTimezone=true" +
                            "&serverTimezone=UTC", properties);
                } catch (SQLException e){
                    e.printStackTrace();
                    connStatus.setText("Соединения нет");
                    return;
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                    connStatus.setText("Нет драйвера");
                    return;
                }
                connStatus.setText("Соединение есть");
            }
        });
    }

    private void InitForm(){
        serverField.setText("localhost:3306");
    }

    public void SaveParams(AbstractContainer container) {
        Map<String, String> result = new HashMap<>();

        result.put("server", serverField.getText());
        result.put("login", loginField.getText());
        result.put("pass", passField.getText());

        params.AddDBParam(result);

        db.SetUsername(loginField.getText());
        db.SetPassword(passField.getText());
    }

    public void Show(AbstractContainer container) {
        params = (DataBaseParams) container;
        db_settings.setVisible(true);
    }
}
