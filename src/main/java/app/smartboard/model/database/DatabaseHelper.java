package app.smartboard.model.database;

import app.smartboard.model.Profile;
import app.smartboard.model.Project;
import app.smartboard.model.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper implements DataAccessObject {

    private ResultSet result;
    private ByteArrayOutputStream byteArrayOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ByteArrayInputStream byteArrayInputStream;
    private ObjectInputStream objectInputStream;
    private static final String SELECT_QUOTE_QUERY = "SELECT * FROM quote WHERE ROWID = ?";
    private static final String CREATE_USER_QUERY = "INSERT INTO user (username, password, profile) VALUES (?, ?, ?)";
    private static final String SELECT_USER_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String UPDATE_PROFILE_QUERY = "UPDATE user SET profile = ? WHERE username = ?";
    private static final String SELECT_PROJECT_QUERY = "SELECT * FROM project WHERE username = ?";
    private static final String DELETE_PROJECT_QUERY = "DELETE FROM project WHERE username = ?";
    private static final String INSERT_PROJECT_QUERY = "INSERT INTO project (username, project) VALUES (?, ?)";

    @Override
    public String getQuote(int rowID) {

        String quote = null;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUOTE_QUERY)) {

            statement.setInt(1, rowID);
            result = statement.executeQuery();

            if (result.next()) {
                // Get quote on rowID
                quote = result.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quote;
    }

    @Override
    public int createUser(String username, String password, Profile profile) throws SQLException, IOException {

        int success = 0;
        byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {

            objectOutputStream.writeObject(profile);
            objectOutputStream.flush();

            // Create user account
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBytes(3, byteArrayOutputStream.toByteArray());
            success = statement.executeUpdate();
        }
        return success;
    }

    @Override
    public User getUser(String username, String password) throws SQLException, IOException, ClassNotFoundException {

        byte[] userProfile;

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) {

            // Get user account
            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();

            if (result.next()) {

                // Create user object
                User user = new User();

                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));

                userProfile = result.getBytes("profile");

                // Reconstruct user profile on condition
                if (userProfile != null) {
                    this.byteArrayInputStream = new ByteArrayInputStream(userProfile);
                    this.objectInputStream = new ObjectInputStream(this.byteArrayInputStream);
                    user.setProfile((Profile) objectInputStream.readObject());
                    this.objectInputStream.close();
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

            // Update profile object
            statement.setBytes(1, byteArrayOutputStream.toByteArray());
            statement.setString(2, username);
            statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Project> getProjects(String username) throws SQLException {

        byte[] project;
        ArrayList<Project> projects = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PROJECT_QUERY)) {

            statement.setString(1, username);
            result = statement.executeQuery();

            // Get projects on condition
            if (result.next()) {
                project = result.getBytes("project");
                // Reconstruct projects object on condition
                if (project != null) {
                    this.byteArrayInputStream = new ByteArrayInputStream(project);
                    this.objectInputStream = new ObjectInputStream(this.byteArrayInputStream);
                    projects = (ArrayList<Project>) this.objectInputStream.readObject();
                    this.objectInputStream.close();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    @Override
    public void setProjects(String username, ArrayList<Project> projects) throws SQLException, IOException {

        // Delete existing projects object
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT_QUERY)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }

        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.objectOutputStream = new ObjectOutputStream(this.byteArrayOutputStream);

        // Insert projects
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_QUERY)) {

            this.objectOutputStream.writeObject(projects);
            this.objectOutputStream.flush();

            // Insert projects object
            statement.setString(1, username);
            statement.setBytes(2, this.byteArrayOutputStream.toByteArray());
            statement.executeUpdate();

        }
    }
}
