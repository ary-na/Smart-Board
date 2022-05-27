package app.smartboard.controller;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.Model;
import app.smartboard.model.Project;
import app.smartboard.model.User;
import app.smartboard.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogInController extends BaseController {

    private Stage stage;
    public TextField username;
    public TextField psw;
    public Label errorLabel;
    public Button logInButton;
    public Hyperlink signUpHyperlink;

    public LogInController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On log in button click
    public void onLogInButtonClick(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        // On invalid input condition
        if (!validInput()) {
            this.errorLabel.setText("Enter username and password");
            this.username.requestFocus();
        } else {

            // Get user
            User user = this.model.getDatabaseHelper().getUser(username.getText().trim(), psw.getText().trim());
            ArrayList<Project> projects = new ArrayList<>();
            ObservableList<Tab> projectTabs = FXCollections.observableArrayList();

            // On login successful condition
            if (user != null) {

                System.out.println("User Logged In");

                // Set the current user
                this.model.setCurrentUser(user);

                // Test -> to be deleted
                projects = this.model.getDatabaseHelper().getProjects(this.model.getCurrentUser().getUsername());

                // Get projects from database
                this.model.setProjects(this.model.getDatabaseHelper().getProjects(this.model.getCurrentUser().getUsername()));
                this.model.getProjectViewModel().setProjectTabs(projectTabs);

                // Test -> to be deleted
                projects.forEach(project -> {
                    System.out.println(project.getName());
                });

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

            } else {
                this.errorLabel.setText("Incorrect username or password");
            }
        }
    }

    // On sign up hyperlink click
    public void onSignUpHyperlinkClick(ActionEvent event) throws IOException {

        System.out.println("onSignUpHyperLinkClick");

        // Display Sign Up view
        viewFactory.displaySignUpView();

        // Close Log In stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    // Input validation
    private boolean validInput() {
        return (!this.username.getText().isEmpty() && this.username.getText().length() <= 45) && (!this.psw.getText().isEmpty() && this.psw.getText().length() <= 45);
    }
}
