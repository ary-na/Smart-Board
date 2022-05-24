package app.smartboard.model.database;

import app.smartboard.model.Profile;
import app.smartboard.model.Project;
import app.smartboard.model.User;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccessObject {
    String getQuote(int rowID);

    User getUser(String username, String password) throws SQLException, IOException, ClassNotFoundException;

    int createUser(String username, String password, Profile profile) throws SQLException, IOException;

    void updateProfile(String username, Profile profile) throws SQLException, IOException;

    ArrayList<Project> getProjects(String username) throws SQLException;

    void setProjects(String username, ArrayList<Project> projects) throws SQLException, IOException;
}
