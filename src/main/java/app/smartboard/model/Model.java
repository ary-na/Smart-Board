package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;

public class Model {

    private static Model modelInstance;
    private final DatabaseHelper databaseHelper;
    private User currentUser;

    public Model() {
        this.databaseHelper = new DatabaseHelper();
    }

    public synchronized static Model getModelInstance() {
        if (modelInstance == null)
            modelInstance = new Model();
        return modelInstance;
    }

    public DatabaseHelper getDatabaseHelper() {
        return this.databaseHelper;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
