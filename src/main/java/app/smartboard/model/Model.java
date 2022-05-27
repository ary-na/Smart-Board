package app.smartboard.model;

import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.viewmodel.ColumnViewModel;
import app.smartboard.model.viewmodel.ProjectViewModel;
import app.smartboard.model.viewmodel.TaskViewModel;
import app.smartboard.model.viewmodel.WorkspaceViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Model {

    private WorkspaceViewModel workspaceViewModel;
    private ProjectViewModel projectViewModel;
    private ColumnViewModel columnViewModel;
    private TaskViewModel taskViewModel;
    private final DatabaseHelper databaseHelper;
    private User currentUser;
    private ArrayList<Project> projects;
    private final NameableFactory nameableFactory;

    public Model() {
        this.workspaceViewModel = new WorkspaceViewModel();
        this.projectViewModel = new ProjectViewModel();
        this.columnViewModel = new ColumnViewModel();
        this.taskViewModel = new TaskViewModel();
        this.databaseHelper = new DatabaseHelper();
        this.projects = new ArrayList<>();
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

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }

    public void setTaskViewModel(TaskViewModel taskViewModel) {
        this.taskViewModel = taskViewModel;
    }

    public int getProjectIndex() {
        return this.projectViewModel.getTabPane().getSelectionModel().getSelectedIndex();
    }

    public int getColumnIndex(VBox column) {
        return this.projects.get(this.getProjectIndex()).getColumn().indexOf(this.columnViewModel.getColumnMap().get(column));
    }

    public int getTaskIndex(VBox task) {
        return this.projects.get(this.getProjectIndex()).getColumn().get(this.getColumnIndex(this.getColumnViewModel().getColumn())).getTask().indexOf(this.taskViewModel.getTaskMap().get(task));
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


}
