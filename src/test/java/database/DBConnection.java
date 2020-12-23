package database;

import utils.PropertyReader;

import java.sql.*;

public class DBConnection {
    String user = System.getenv().getOrDefault("user", PropertyReader.getProperty("user"));
    String dbPassword = System.getenv().getOrDefault("dbPassword", PropertyReader.getProperty("dbPassword"));
    private Connection connect = null;
    private Statement statement = null;

    public void connect() {
        try {
            connect = DriverManager
                    .getConnection(String.format("jdbc:mysql://localhost/instagram_users?"
                            + "user=%s&password=%s&useSSL=true&serverTimezone=GMT", user, dbPassword));
            statement = connect.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int addProfileName(String username) {
        try {
            return statement
                    .executeUpdate(String.format("INSERT INTO instagram_users.users (users.username) VALUES ('%s')", username));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Boolean containsProfileName(String username) {
        try {
            ResultSet resultSet = statement
                    .executeQuery(String.format("SELECT count(*) FROM instagram_users.users WHERE username = '%s'", username));
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception ignored) {
        }
    }
}
