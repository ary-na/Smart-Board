package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.viewmodel.ColumnViewModel;
import app.smartboard.model.viewmodel.ProjectViewModel;
import app.smartboard.model.viewmodel.WorkspaceViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public class Model {

    private WorkspaceViewModel workspaceViewModel;
    private ProjectViewModel projectViewModel;
    private ColumnViewModel columnViewModel;
    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private ObservableList<Project> projects;
    private final NameableFactory nameableFactory;

    public Model() {
        this.workspaceViewModel = new WorkspaceViewModel();
        this.projectViewModel = new ProjectViewModel();
        this.columnViewModel = new ColumnViewModel();
        this.databaseHelper = new DatabaseHelper();
        this.projects = FXCollections.observableArrayList();
        this.nameableFactory = new NameableFactory();
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

    public ProjectViewModel getProjectViewModel() {
        return projectViewModel;
    }

    public void setProjectViewModel(ProjectViewModel projectViewModel) {
        this.projectViewModel = projectViewModel;
    }

    public ColumnViewModel getColumnViewModel() {
        return columnViewModel;
    }

    public void setColumnViewModel(ColumnViewModel columnViewModel) {
        this.columnViewModel = columnViewModel;
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ObservableList<Project> projects) {
        this.projects = projects;
    }

    public Nameable createNameable(String nameableType, String nameableName) {
        Nameable nameable = nameableFactory.createNameable(nameableType, nameableName);
        if (nameable instanceof Project)
            this.projects.add((Project) nameable);
        return nameable;
    }
}
