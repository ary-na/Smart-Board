package app.smartboard.model;

import java.sql.*;

public class DatabaseHelper {
    private static DatabaseHelper databaseHelperInstance;
    private Connection connection = null;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/smart_board?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "ArianS3910902";
    private static final String INSERT_QUERY = "INSERT INTO user (user_name, psw, first_name, last_name, profile_photo) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM user WHERE user_name = ? AND psw = ?";

    private DatabaseHelper() { }

    public synchronized static DatabaseHelper getDatabaseHelperInstance() {
        if (databaseHelperInstance == null)
            databaseHelperInstance = new DatabaseHelper();
        return databaseHelperInstance;
    }

    public void createUser(String userName, String psw, String firstName, String lastName, String profilePhoto) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            if(connection != null){
                PreparedStatement values = connection.prepareStatement(INSERT_QUERY); {
                    values.setString(1, userName);
                    values.setString(2, psw);
                    values.setString(3, firstName);
                    values.setString(4, lastName);
                    values.setString(5, profilePhoto);
                    values.executeUpdate();
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean validate(String userName, String psw) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            if(connection != null){
                PreparedStatement values = connection.prepareStatement(SELECT_QUERY); {
                    values.setString(1, userName);
                    values.setString(2, psw);
                    ResultSet result = values.executeQuery();
                    if (result.next()){
                        return true;
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
