package database;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

import java.sql.*;

@Log4j2
public class DBConnection {
    String user = System.getProperty("user");
    String dbPassword = System.getProperty("dbPassword");
    private Connection connect = null;
    private Statement statement = null;

    public void connect() {
        try {
            log.info(String.format("Connecting to the database with user '%s' and password '%s'", user, dbPassword));
            connect = DriverManager
                    .getConnection(String.format("jdbc:mysql://localhost/instagram_users?"
                            + "user=%s&password=%s&useSSL=true&serverTimezone=GMT", user, dbPassword));
            statement = connect.createStatement();
        } catch (SQLException e) {
            log.fatal(e.getLocalizedMessage());
            Assert.fail(String.format("Could not connect to the database with user '%s' and password '%s'", user, dbPassword));
        }
    }

    public int addProfileName(String username) {
        try {
            log.info(String.format("Adding profile name '%s' to the database", username));
            return statement
                    .executeUpdate(String.format("INSERT INTO instagram_users.users (users.username) VALUES ('%s')", username));
        } catch (SQLException e) {
            log.fatal(e.getLocalizedMessage());
            Assert.fail(String.format("Could not add profile name '%s' to the database", username));
        }
        return 0;
    }

    public Boolean containsProfileName(String username) {
        try {
            log.info(String.format("Checking if database already contains profile name '%s'", username));
            ResultSet resultSet = statement
                    .executeQuery(String.format("SELECT count(*) FROM instagram_users.users WHERE username = '%s'", username));
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            log.fatal(e.getLocalizedMessage());
            Assert.fail(String.format("Could not check if database already contains profile name '%s'", username));
        }
        return false;
    }

    public void close() {
        log.info("Closing connection with database");
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
