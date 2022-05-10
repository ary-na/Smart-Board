package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.controller.*;
import app.smartboard.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
