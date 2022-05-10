package app.smartboard.model;

import app.smartboard.controller.WorkspaceController;
import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.view.WorkspaceViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class Model {


    private static Model modelInstance;
    private WorkspaceViewModel workspaceViewModel;
    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private final ObservableList<Project> projects;
    private final ObservableList<Tab> projectUI;

    private Model() {
        this.workspaceViewModel = new WorkspaceViewModel();
        this.databaseHelper = new DatabaseHelper();
        this.projects = FXCollections.observableArrayList();
        this.projectUI = FXCollections.observableArrayList();
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

    public WorkspaceViewModel getViewModel() {
        return workspaceViewModel;
    }

    public void setViewModel(WorkspaceViewModel viewModel) {
        this.workspaceViewModel = viewModel;
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }

    public ObservableList<Tab> getProjectUI() {
        return projectUI;
    }
}
