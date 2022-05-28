package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.viewmodel.ColumnViewModel;
import app.smartboard.model.viewmodel.ProjectViewModel;
import app.smartboard.model.viewmodel.TaskViewModel;
import app.smartboard.model.viewmodel.WorkspaceViewModel;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

// Model class
public class Model {

    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private ArrayList<Project> projects;
    private final NameableFactory nameableFactory;
    private final WorkspaceViewModel workspaceViewModel;
    private final ProjectViewModel projectViewModel;
    private final ColumnViewModel columnViewModel;
    private final TaskViewModel taskViewModel;

    public Model() {

        this.databaseHelper = new DatabaseHelper();
        this.projects = new ArrayList<>();
        this.nameableFactory = new NameableFactory();
        this.workspaceViewModel = new WorkspaceViewModel();
        this.projectViewModel = new ProjectViewModel();
        this.columnViewModel = new ColumnViewModel();
        this.taskViewModel = new TaskViewModel();
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

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public Nameable createNameable(String nameableType, String nameableName) {
        Nameable nameable = nameableFactory.createNameable(nameableType, nameableName);
        if (nameable instanceof Project)
            this.projects.add((Project) nameable);
        return nameable;
    }

    public WorkspaceViewModel getWorkspaceViewModel() {
        return workspaceViewModel;
    }

    public ProjectViewModel getProjectViewModel() {
        return projectViewModel;
    }

    // Return selected project index
    public int getProjectIndex() {
        return this.projectViewModel.getTabPane().getSelectionModel().getSelectedIndex();
    }

    public ColumnViewModel getColumnViewModel() {
        return columnViewModel;
    }

    // Return selected column index
    public int getColumnIndex(VBox column) {
        return this.projects.get(this.getProjectIndex()).getColumn().indexOf(this.columnViewModel.getColumnMap().get(column));
    }

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }

    // Return selected task index
    public int getTaskIndex(VBox task) {
        return this.projects.get(this.getProjectIndex()).getColumn().get(this.getColumnIndex(this.getColumnViewModel().getColumn())).getTask().indexOf(this.taskViewModel.getTaskMap().get(task));
    }
}
