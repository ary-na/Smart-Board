package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.viewmodel.WorkspaceViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public class Model {

    private WorkspaceViewModel workspaceViewModel;
    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private ObservableList<Project> projects;
    private ObservableList<Tab> projectUI;
    private final NameableFactory nameableFactory = new NameableFactory();
    private Nameable nameable;

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

    public WorkspaceViewModel getWorkspaceViewModel() {
        return workspaceViewModel;
    }

    public void setWorkspaceViewModel(WorkspaceViewModel workspaceViewModel) {
        this.workspaceViewModel = workspaceViewModel;
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ObservableList<Project> projects) {
        this.projects = projects;
    }

    public ObservableList<Tab> getProjectUI() {
        return projectUI;
    }

    public void setProjectUI(ObservableList<Tab> projectUI) {
        this.projectUI = projectUI;
    }

    public Nameable createNameable(String nameableType, String nameableName) {
        this.nameable = nameableFactory.createNameable(nameableType, nameableName);
        if (this.nameable instanceof Project)
            this.projects.add((Project) nameable);
        return this.nameable;
    }
}
