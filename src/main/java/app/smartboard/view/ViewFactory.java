package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.controller.*;
import app.smartboard.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Code sourced and adapted from:
 * https://www.udemy.com/course/advanced-programming-with-javafx-build-an-email-client/
 * https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/968af113fc73cc16454d26ab95db0fa5962b7a34
 */

public class ViewFactory {

    private Model model;
    private Stage stage;
    private Scene scene;

    private FXMLLoader fxmlLoader;

    public ViewFactory(Model model) {
        this.model = model;
    }

    // Create Log In stage
    public void displayLoginView() throws IOException {

        System.out.println("Log In View");
        BaseController controller = new LogInController(model, this, "view/log-in-view.fxml");
        initializeStage(controller, "Smart Board - Log In", false);
    }

    // Create Sign Up stage
    public void displaySignUpView() throws IOException {

        System.out.println("Sign Up View");
        BaseController controller = new SignUpController(model, this, "view/sign-up-view.fxml");
        initializeStage(controller, "Smart Board - Sign Up", false);
    }

    // Create Workspace stage
    public void displayWorkspaceView() throws IOException {

        System.out.println("Workspace View");
        BaseController controller = new WorkspaceController(model, this, "view/workspace-view.fxml");
        initializeStage(controller, "Smart Board - Workspace", true);

    }

    public void displayEditProfileView(Stage owner) throws IOException {

        System.out.println("Edit Profile View");
        BaseController controller = new EditProfileController(model, this, "view/edit-profile-view.fxml");
        initializeChildStage(controller, owner, "Edit Profile", false);

    }

    public void displayCreateProjectView(Stage owner) throws IOException {

        System.out.println("Create Project View");
        BaseController controller = new CreateProjectController(model, this, "view/create-project-view.fxml");
        initializeChildStage(controller, owner, "Create Project", false);

    }

    public void displayRenameProjectView(Stage owner) throws IOException {

        System.out.println("Create Rename View");
        BaseController controller = new RenameProjectController(model, this, "view/rename-project-view.fxml");
        initializeChildStage(controller, owner, "Rename Project", false);

    }

    public void displayDeleteProjectView(Stage owner) throws IOException {

        System.out.println("Create Delete View");
        BaseController controller = new DeleteProjectController(model, this, "view/delete-project-view.fxml");
        initializeChildStage(controller, owner, "Delete Project", false);

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
    private void initializeChildStage(BaseController controller, Stage owner, String title, Boolean resizable) throws IOException {

        this.fxmlLoader = new FXMLLoader(SmartBoardApplication.class.getResource(controller.getFxml()));
        this.fxmlLoader.setController(controller);
        this.scene = new Scene(fxmlLoader.load());
        this.stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.showAndWait();

    }

    // Close stage
    public void closeStage(Stage stage) {
        stage.close();
    }


}
