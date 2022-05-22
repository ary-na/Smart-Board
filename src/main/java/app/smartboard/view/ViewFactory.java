package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.controller.*;
import app.smartboard.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Code sourced and adapted from:
 * https://www.udemy.com/course/advanced-programming-with-javafx-build-an-email-client/
 * https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/968af113fc73cc16454d26ab95db0fa5962b7a34
 */

public class ViewFactory {

    private final Model model;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public ViewFactory(Model model) {
        this.model = model;
    }

    // Display Log In stage
    public void displayLoginView() throws IOException {

        System.out.println("Log In View stage");
        BaseController controller = new LogInController(this.model, this, "view/log-in-view.fxml");
        initializeStage(controller, "Smart Board - Log In", false);

    }

    // Display Sign Up stage
    public void displaySignUpView() throws IOException {

        System.out.println("Sign Up View stage");
        BaseController controller = new SignUpController(this.model, this, "view/sign-up-view.fxml");
        initializeStage(controller, "Smart Board - Sign Up", false);
    }

    // Display Workspace stage
    public void displayWorkspaceView() throws IOException {

        System.out.println("Workspace View stage");
        BaseController controller = new WorkspaceController(this.model, this, "view/workspace-view.fxml");
        initializeStage(controller, "Smart Board - Workspace", true);

    }

    // Display Create Project stage
    public void displayCreateProjectView(Stage owner) throws IOException {

        System.out.println("Create Project View stage");
        BaseController controller = new CreateProjectController(this.model, this, "view/create-project-view.fxml");
        initializeChildStage(controller, owner, "Create Project");

    }

    // Display Rename Project stage
    public void displayRenameProjectView(Stage owner) throws IOException {

        System.out.println("Create Rename Project View stage");
        BaseController controller = new RenameProjectController(this.model, this, "view/rename-project-view.fxml");
        initializeChildStage(controller, owner, "Rename Project");

    }

    // Display Delete Project stage
    public void displayDeleteProjectView(Stage owner) throws IOException {

        System.out.println("Create Delete View stage");
        BaseController controller = new DeleteProjectController(this.model, this, "view/delete-project-view.fxml");
        initializeChildStage(controller, owner, "Delete Project");

    }

    // Display Create Column stage
    public void displayCreateColumnView(Stage owner) throws IOException {

        System.out.println("Create Column View stage");
        BaseController controller = new CreateColumnController(this.model, this, "view/create-column-view.fxml");
        initializeChildStage(controller, owner, "Create Column");

    }

    // Display Rename Column stage
    public void displayRenameColumnView(Stage owner) throws IOException {

        System.out.println("Create Rename Column View stage");
        BaseController controller = new RenameColumnController(this.model, this, "view/rename-column-view.fxml");
        initializeChildStage(controller, owner, "Rename Column");

    }

    // Display Delete Column stage
    public void displayDeleteColumnView(Stage owner) throws IOException {

        System.out.println("Create Delete Column View stage");
        BaseController controller = new DeleteColumnController(this.model, this, "view/delete-column-view.fxml");
        initializeChildStage(controller, owner, "Delete Column");

    }

    // Display Create Task stage
    public void displayCreateTaskView(Stage owner) throws IOException {

        System.out.println("Create Task View stage");
        BaseController controller = new CreateTaskController(this.model, this, "view/create-task-view.fxml");
        initializeChildStage(controller, owner, "Create Task");

    }

    // Display Edit Project stage
    public void displayEditProfileView(Stage owner) throws IOException {

        System.out.println("Edit Profile View stage");
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

        ProjectView viewProject = new ProjectView((Project) nameable);

        // Add tab to list
        this.model.getProjectViewModel().getProjectTabs().add(viewProject);

        // Add tab to tab pane
        this.model.getProjectViewModel().getTabPane().getTabs().add(viewProject);

        this.model.getProjectViewModel().getTabPane().getSelectionModel().select(viewProject);
        this.model.getWorkspaceViewModel().setEmptyWorkspace(false);

    }

    // Initialize Column
    public void initializeColumn(Nameable nameable) {

        ProjectView projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());

        ColumnView viewColumn = new ColumnView((Column) nameable);
        projectView.addColumnView(viewColumn);
        this.model.getColumnViewModel().getColumnMap().put(viewColumn, (Column) nameable);


    }

    // Initialize Task
    public void initializeTask(Nameable nameable) {

        VBox columnVBox = this.model.getColumnViewModel().getColumn();
        TaskView taskView = new TaskView((Task) nameable);

        ProjectView projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
        projectView.getColumnViews().get(model.getColumnIndex(columnVBox)).addTaskView(taskView);

        this.model.getTaskViewModel().getTaskMap().put(taskView, (Task) nameable);

    }

    // Close stage
    public void closeStage(Stage stage) {
        stage.close();
    }

}
