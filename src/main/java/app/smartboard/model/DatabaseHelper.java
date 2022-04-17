package app.smartboard.model;

import java.sql.*;

public class DatabaseHelper {
    private static DatabaseHelper databaseHelperInstance;
    private PreparedStatement statement;
    private ResultSet result;
    private int success;
    private static final String INSERT_USER = "INSERT INTO user (username, password) VALUES (?, ?)";
    private static final String INSERT_PROFILE = "INSERT INTO profile (username, first_name, last_name) VALUES (?, ?, ?)";

    private static final String SELECT_USER_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String SELECT_QUOTE_QUERY = "SELECT * FROM quote WHERE ROWID = ?";

    private DatabaseHelper() {}

    public synchronized static DatabaseHelper getDatabaseHelperInstance() {
        if (databaseHelperInstance == null)
            databaseHelperInstance = new DatabaseHelper();
        return databaseHelperInstance;
    }

    private Connection connection() {
        Connection connection = null;
        final String DATABASE_URL = "jdbc:sqlite:sb.db";
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void createUser(String username, String firstName, String lastName, String password) {
        try (Connection connection = this.connection();
             PreparedStatement firstStatement = connection.prepareStatement(INSERT_USER)) {
            firstStatement.setString(1, username);
            firstStatement.setString(2, password);
            success = firstStatement.executeUpdate();
            if(success > 0){
                PreparedStatement secondStatement = connection.prepareStatement(INSERT_PROFILE); {
                    secondStatement.setString(1, username);
                    secondStatement.setString(2, firstName);
                    secondStatement.setString(3, lastName);
                    secondStatement.executeUpdate();
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean validate(String username, String password) {
        try (Connection connection = this.connection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String getQuote(int rowID) {
        String quote = null;
        try (Connection connection = this.connection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUOTE_QUERY)) {
            statement.setInt(1, rowID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                quote = result.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quote;
    }
}
