package z_wizard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.mysql.cj.jdbc.Driver;
import z_wizard.project.JsonParser;

public class DataBase {

    private  String username = "Ogatir";
    private  String password = "qaws200"; //изменить на пароль, который выбран при установке


    private PreparedStatement preparedStatement = null;
    private int status = 0;

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

    private String parsing(String path, String param) throws FileNotFoundException {

        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        String result = null;
        JsonReader jsonReader = new JsonReader(new FileReader(path));
        JsonElement jsonElement = parser.parse(jsonReader);

        if(jsonElement.isJsonObject()){
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement element = jsonObject.get(param);
            result = element.toString();
        }

        return result;
    }

    //функция добавления в бд для ZMAP
    public void insertZmap(String csvFile) {
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


    //функция добавления в бд для ZDNS
    public int insertZdns(String jsonFile)  {

        Connection connection = null;
        try {
            connection = gettingConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO zdns_result (name, class, status," +
                    " timestamp, data) VALUES (?, ?, ?, ?, ? )");

            String name = parsing(jsonFile, "name");
            String class1 = parsing(jsonFile, "class");
            String status1 = parsing(jsonFile, "status");
            String time = parsing(jsonFile, "timestamp");
            String data = parsing(jsonFile, "data");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, class1);
            preparedStatement.setString(3, status1);
            preparedStatement.setString(4, time);
            preparedStatement.setString(5, data);
            status = preparedStatement.executeUpdate();

        } catch (Exception e){
             e.printStackTrace();
        } finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (Exception e){
                 e.printStackTrace();
            }
        }
        return status;
    }

    //функция добавления в бд для ZGrab
    public int insertZgrab(String jsonFile)  {

        Connection connection = null;
        try {
            connection = gettingConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO zgrab_result (ip, domain, timestamp, " +
                    "data) VALUES (?, ?, ?, ?)");

            String ip = parsing(jsonFile, "ip");
            String domain = parsing(jsonFile, "domain");
            String time = parsing(jsonFile, "timestamp");
            String data = parsing(jsonFile, "data");
            preparedStatement.setString(1, ip);
            preparedStatement.setString(2, domain);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, data);
            status = preparedStatement.executeUpdate();

        } catch (Exception e){
             e.printStackTrace();
        } finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return status;
    }


}
