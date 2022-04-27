package app.smartboard.model.database;

import app.smartboard.model.Nameable;
import app.smartboard.model.Profile;
import app.smartboard.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public interface DataAccessObject {
    String getQuote(int rowID);

    User getUser(String username, String password) throws SQLException, IOException, ClassNotFoundException;

    User createUser(String username, String password, Profile profile) throws SQLException, IOException;

    void updateProfile(String username, Profile profile) throws SQLException, IOException;
}
