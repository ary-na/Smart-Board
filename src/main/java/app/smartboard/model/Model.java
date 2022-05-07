package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;

import java.util.LinkedList;

public class Model {

    private static Model modelInstance;
    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private final LinkedList<Project> projects;

    private Model() {
        this.databaseHelper = new DatabaseHelper();
        this.projects = new LinkedList<>();
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

    public LinkedList<Project> getProjects() {
        return projects;
    }

    public void setProjects(Project project) {
        this.projects.addLast(project);
    }
}
