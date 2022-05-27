package app.smartboard.controller;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.*;
import app.smartboard.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignUpController extends BaseController {

    private Stage stage;
    public TextField username;
    public TextField firstName;
    public TextField lastName;
    public TextField password;
    public Label errorLabel;

    public SignUpController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On sign up button click
    public void onSignUpButtonClick(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        // On invalid input condition
        if (!validInput()) {
            this.errorLabel.setText("All fields are required");
            this.username.requestFocus();
        } else {

            int accountCreated = 0;
            System.out.println("onSignUpButtonClick");

            // Create user profile
            Profile profile = new Profile();
            profile.setFirstName(firstName.getText().trim());
            profile.setLastName(lastName.getText().trim());

            try {
                // Create user account
                accountCreated = this.model.getDatabaseHelper().createUser(username.getText().trim(), password.getText().trim(), profile);
                System.out.println(accountCreated);
            } catch (SQLException e) {
                System.out.println("User name already exists");
                this.errorLabel.setText("User name already exists");
                this.username.requestFocus();
            }


            // On account creation successful condition
            if (accountCreated == 1) {
                System.out.println("User created");

                // Get user
                User user = this.model.getDatabaseHelper().getUser(username.getText(), password.getText());
                ArrayList<Project> projects = new ArrayList<>();
                ObservableList<Tab> projectTabs = FXCollections.observableArrayList();

                // Set the current user
                this.model.setCurrentUser(user);
                this.model.setProjects(projects);
                this.model.getProjectViewModel().setProjectTabs(projectTabs);

                // Display Workspace view
                viewFactory.displayWorkspaceView();

                // Close sign up In stage
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                viewFactory.closeStage(stage);

                // Set first name and profile image
                this.model.getWorkspaceViewModel().setUserFirstName(this.model.getCurrentUser().getProfile().getFirstName());
                this.model.getWorkspaceViewModel().setUserImage(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/default-profile-photo.png"))));
            }
        }
    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) throws IOException {

        System.out.println("onCancelButtonClick");

        // Display log in view
        viewFactory.displayLoginView();

        // Close sign up stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    // Input validation
    private boolean validInput() {
        return (!this.username.getText().isEmpty() && this.username.getText().length() <= 45) && (!this.password.getText().isEmpty() && this.password.getText().length() <= 45) && (!this.firstName.getText().isEmpty() && this.firstName.getText().length() <= 45) && (!this.lastName.getText().isEmpty() && this.lastName.getText().length() <= 45);
    }
}
