package app.smartboard.controller;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.Model;
import app.smartboard.model.Nameable;
import app.smartboard.model.Project;
import app.smartboard.model.User;
import app.smartboard.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController extends BaseController {

    @FXML
    public Hyperlink signUpHyperlink;

    @FXML
    private Button logInButton;

    @FXML
    private TextField psw;

    @FXML
    private TextField username;

    private Stage stage;

    public LogInController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }


    @FXML
    public void onLogInButtonClick(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        User user = this.model.getDatabaseHelper().getUser(username.getText().trim(), psw.getText().trim());
        ObservableList<Project> projects = FXCollections.observableArrayList();
        ObservableList<Tab> projectUI = FXCollections.observableArrayList();

        if (user != null) {

            System.out.println("User Logged In");

            // Set the current user
            this.model.setCurrentUser(user);
            this.model.setProjects(projects);
            this.model.setProjectUI(projectUI);

            // Load user data
            if (this.model.getCurrentUser().getProfile().getProfilePhoto() == null) {
                this.model.getWorkspaceViewModel().setUserImage(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/default-profile-photo.png"))));
            }
            this.model.getWorkspaceViewModel().setUserFirstName(this.model.getCurrentUser().getProfile().getFirstName());

            // Display Workspace view
            viewFactory.displayWorkspaceView();

            // Close Log In stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            viewFactory.closeStage(stage);
        }
    }

    public void onSignUpHyperLinkClick(ActionEvent event) throws IOException {

        System.out.println("onSignUpHyperLinkClick");
        // Display Sign Up view
        viewFactory.displaySignUpView();

        // Close Log In stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
