package z_wizard;

import java.sql.*;
import java.util.Properties;

public class DataBase {

    private  String username = "Ogatir";
    private  String password = "qaws200"; //изменить на пароль, который выбран при установке

    public void SetUsername(String input){
        username = input;
    }

    public void SetPassword(String pass){
        password = pass;
    }

    private Connection gettingConnection() throws ClassNotFoundException, SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zwizard" +
                        "?useTimezone=true" +
                        "&serverTimezone=UTC", properties);

        return connection;
    }


    //функция добавления в бд для ZMAP
    public void importZmapAllFields(String csvFile) {
        try (Connection connector = gettingConnection()) {
            String loadQuery = "LOAD DATA LOCAL INFILE '" + csvFile + "'" +
                    " INTO TABLE zmap_result" +
                    " FIELDS TERMINATED BY ','" +
                    " LINES TERMINATED BY '\n' " +
                    "(saddr, saddrraw, daddr, daddrraw, ipid, ttl, sport, dport, seqnum, acknum, window, " +
                    "classification, success, repeats, cooldown, timestampstr, timestampts, timestampus)";
            Statement statement = connector.createStatement();
            statement.execute(loadQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
