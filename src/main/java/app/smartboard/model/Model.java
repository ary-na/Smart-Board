package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.viewmodel.WorkspaceViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public class Model {

    private WorkspaceViewModel workspaceViewModel;
    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private final ObservableList<Nameable> projects;
    private final ObservableList<Tab> projectUI;

    public Model() {
        this.workspaceViewModel = new WorkspaceViewModel();
        this.databaseHelper = new DatabaseHelper();
        this.projects = FXCollections.observableArrayList();
        this.projectUI = FXCollections.observableArrayList();
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

    public ObservableList<Nameable> getProjects() {
        return projects;
    }

    public ObservableList<Tab> getProjectUI() {
        return projectUI;
    }
}
