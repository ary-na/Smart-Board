package app.smartboard.model.database;

import app.smartboard.model.Profile;
import app.smartboard.model.User;

import java.io.*;
import java.sql.*;

public class DatabaseHelper implements DataAccessObject {

    private ResultSet result;

    private ByteArrayOutputStream byteArrayOutputStream;
    private ObjectOutputStream objectOutputStream;

    private static final String SELECT_QUOTE_QUERY = "SELECT * FROM quote WHERE ROWID = ?";
    private static final String CREATE_USER_QUERY = "INSERT INTO user (username, password, profile) VALUES (?, ?, ?)";
    private static final String SELECT_USER_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String UPDATE_PROFILE_QUERY = "UPDATE user SET profile = ? WHERE username = ?";

    public DatabaseHelper() {
    }

    @Override
    public String getQuote(int rowID) {
        String quote = null;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUOTE_QUERY)) {
            statement.setInt(1, rowID);
            result = statement.executeQuery();
            if (result.next()) {
                quote = result.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quote;
    }

    @Override
    public User createUser(String username, String password, Profile profile) throws SQLException, IOException {

        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {

            objectOutputStream.writeObject(profile);
            objectOutputStream.flush();

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBytes(3, byteArrayOutputStream.toByteArray());
            statement.execute();
        }
        return null;
    }

    @Override
    public User getUser(String username, String password) throws SQLException, IOException, ClassNotFoundException {

        byte[] userProfile;

        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) {

            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();

            if (result.next()) {

                User user = new User();

                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));

                userProfile = result.getBytes("profile");

                if (userProfile != null) {
                    byteArrayInputStream = new ByteArrayInputStream(userProfile);
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    user.setProfile((Profile) objectInputStream.readObject());
                    objectInputStream.close();
                }
                return user;
            }
        }
        return null;
    }

    @Override
    public void updateProfile(String username, Profile profile) throws SQLException, IOException {

        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROFILE_QUERY)) {
            objectOutputStream.writeObject(profile);
            objectOutputStream.flush();
            statement.setBytes(1, byteArrayOutputStream.toByteArray());
            statement.setString(2, username);
            statement.executeUpdate();
        }
    }
}
