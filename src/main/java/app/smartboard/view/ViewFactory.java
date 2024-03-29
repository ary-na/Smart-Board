package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.controller.*;
import app.smartboard.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/*
 * Code sourced and adapted from:
 * https://www.udemy.com/course/advanced-programming-with-javafx-build-an-email-client/
 * https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/968af113fc73cc16454d26ab95db0fa5962b7a34
 * https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/695286c30a82dd7acfa718303d31b3a65301812b
 */

public class ViewFactory {

    private final Model model;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private ProjectView projectView;
    private TaskState taskState = TaskState.DEFAULT;
    private int counter = 0;

    public ViewFactory(Model model) {
        this.model = model;
    }

    // Display log in stage
    public void displayLoginView() throws IOException {

        System.out.println("Create Log In View stage");
        BaseController controller = new LogInController(this.model, this, "view/log-in-view.fxml");
        initializeStage(controller, "Smart Board - Log In", false);

    }

    // Display sign up stage
    public void displaySignUpView() throws IOException {

        System.out.println("Create Sign Up View stage");
        BaseController controller = new SignUpController(this.model, this, "view/sign-up-view.fxml");
        initializeStage(controller, "Smart Board - Sign Up", false);
    }

    // Display workspace stage
    public void displayWorkspaceView() throws IOException {

        System.out.println("Create Workspace View stage");
        BaseController controller = new WorkspaceController(this.model, this, "view/workspace-view.fxml");
        initializeStage(controller, "Smart Board - Workspace", true);

    }

    // Display create project stage
    public void displayCreateProjectView(Stage owner) throws IOException {

        System.out.println("Create Create Project View stage");
        BaseController controller = new CreateProjectController(this.model, this, "view/create-project-view.fxml");
        initializeChildStage(controller, owner, "Create Project");

    }

    // Display rename project stage
    public void displayRenameProjectView(Stage owner) throws IOException {

        System.out.println("Create Rename Project View stage");
        BaseController controller = new RenameProjectController(this.model, this, "view/rename-project-view.fxml");
        initializeChildStage(controller, owner, "Rename Project");

    }

    // Display delete project stage
    public void displayDeleteProjectView(Stage owner) throws IOException {

        System.out.println("Create Delete Project View stage");
        BaseController controller = new DeleteProjectController(this.model, this, "view/delete-project-view.fxml");
        initializeChildStage(controller, owner, "Delete Project");

    }

    // Display create column stage
    public void displayCreateColumnView(Stage owner) throws IOException {

        System.out.println("Create Create Column View stage");
        BaseController controller = new CreateColumnController(this.model, this, "view/create-column-view.fxml");
        initializeChildStage(controller, owner, "Create Column");

    }

    // Display rename column stage
    public void displayRenameColumnView(Stage owner) throws IOException {

        System.out.println("Create Rename Column View stage");
        BaseController controller = new RenameColumnController(this.model, this, "view/rename-column-view.fxml");
        initializeChildStage(controller, owner, "Rename Column");

    }

    // Display delete column stage
    public void displayDeleteColumnView(Stage owner) throws IOException {

        System.out.println("Create Delete Column View stage");
        BaseController controller = new DeleteColumnController(this.model, this, "view/delete-column-view.fxml");
        initializeChildStage(controller, owner, "Delete Column");

    }

    // Display create task stage
    public void displayCreateTaskView(Stage owner) throws IOException {

        System.out.println("Create Create Task View stage");
        BaseController controller = new CreateTaskController(this.model, this, "view/create-task-view.fxml");
        initializeChildStage(controller, owner, "Create Task");

    }

    // Display edit task stage
    public void displayEditTaskView(Stage owner) throws IOException {

        System.out.println("Create Edit Task View stage");
        BaseController controller = new EditTaskController(this.model, this, "view/edit-task-view.fxml");
        initializeChildStage(controller, owner, "Edit Task");

    }

    // Display delete task stage
    public void displayDeleteTaskView(Stage owner) throws IOException {

        System.out.println("Create Delete Task View stage");
        BaseController controller = new DeleteTaskController(this.model, this, "view/delete-task-view.fxml");
        initializeChildStage(controller, owner, "Delete Task");

    }

    // Display edit profile stage
    public void displayEditProfileView(Stage owner) throws IOException {

        System.out.println("Create Edit Profile View stage");
        BaseController controller = new EditProfileController(this.model, this, "view/edit-profile-view.fxml");
        initializeChildStage(controller, owner, "Edit Profile");

    }

    // Initialize stage and inject controller to FXML
    private void initializeStage(BaseController controller, String title, Boolean resizable) throws IOException {

        this.fxmlLoader = new FXMLLoader(SmartBoardApplication.class.getResource(controller.getFxml()));
        this.fxmlLoader.setController(controller);
        this.scene = new Scene(fxmlLoader.load());
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.setTitle(title);
        this.stage.setResizable(resizable);
        this.stage.show();

    }

    // Initialize child stage and inject controller to FXML
    private void initializeChildStage(BaseController controller, Stage owner, String title) throws IOException {

        this.fxmlLoader = new FXMLLoader(SmartBoardApplication.class.getResource(controller.getFxml()));
        this.fxmlLoader.setController(controller);
        this.scene = new Scene(fxmlLoader.load());
        this.stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.showAndWait();

    }

    // Initialize Project
    public void initializeProject(Nameable nameable) {

        this.projectView = new ProjectView((Project) nameable);

        // Add tab to list
        this.model.getProjectViewModel().getProjectMap().put((Project) nameable, projectView);
        this.model.getProjectViewModel().getProjectTabs().add(this.projectView);

        // Add tab to tab pane
        this.model.getProjectViewModel().getTabPane().getTabs().add(this.projectView);

        // Select last project tab
        this.model.getProjectViewModel().getTabPane().getSelectionModel().select(this.projectView);

        // Enable menu items
        this.model.getWorkspaceViewModel().setEmptyWorkspace(false);

    }

    // Initialize Column
    public void initializeColumn(Nameable nameable) {

        // Get project index
        this.projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());

        // Add column to ColumnView
        ColumnView columnView = new ColumnView((Column) nameable);
        this.model.getColumnViewModel().getColumnMap().put(columnView, (Column) nameable);
        this.projectView.addColumnView(columnView);

    }

    // Initialize Task
    public void initializeTask(Nameable nameable) {

        TaskView taskView = new TaskView((Task) nameable);
        taskView.setId(String.valueOf(counter));

        // Add task to TaskView
        this.projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
        this.projectView.getColumnViews().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).addTaskView(taskView);

        // Put task to map
        this.model.getTaskViewModel().getTaskMap().put(taskView, (Task) nameable);

        // Get task UI
        HBox taskContainer = (HBox) taskView.getChildren().get(0);
        VBox left = (VBox) taskContainer.getChildren().get(0);
        Label taskDueDate = (Label) left.getChildren().get(1);

        // Add CSS class
        taskDueDate.getStyleClass().add(TaskState.getCSSClass(this.getTaskState((Task) nameable)));

        counter++;

    }

    // Move task
    public void moveTask(Nameable nameable, int targetColumnIndex) {

        TaskView taskView = new TaskView((Task) nameable);
        taskView.setId(String.valueOf(counter));

        // Add task to TaskView
        this.projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
        this.projectView.getColumnViews().get(targetColumnIndex).addTaskView(taskView);

        // Put task to map
        this.model.getTaskViewModel().getTaskMap().put(taskView, (Task) nameable);

        // Get task UI
        HBox taskContainer = (HBox) taskView.getChildren().get(0);
        VBox left = (VBox) taskContainer.getChildren().get(0);
        Label taskDueDate = (Label) left.getChildren().get(1);

        // Add CSS class
        taskDueDate.getStyleClass().add(TaskState.getCSSClass(this.getTaskState((Task) nameable)));

        counter++;
    }

    // Get task state
    public TaskState getTaskState(Task task) {

        LocalDate taskDate;
        LocalDate today = LocalDate.now();

        // Set task state on condition
        if (task.getDueDate() != null) {
            taskDate = task.getDueDate();
            if (taskDate.isAfter(today) && !task.getCompleted()) {
                setTaskState(TaskState.DEFAULT);
            } else if (task.getCompleted()) {
                setTaskState(TaskState.COMPLETED);
            } else if (taskDate.equals(today) && !task.getCompleted()) {
                setTaskState(TaskState.DUE);
            } else if (taskDate.isBefore(today) && !task.getCompleted()) {
                setTaskState(TaskState.OVERDUE);
            }
        } else {
            setTaskState(TaskState.NO_DATE);
        }

        return taskState;
    }

    // Set task state
    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    // Close stage
    public void closeStage(Stage stage) {
        stage.close();
    }

}
