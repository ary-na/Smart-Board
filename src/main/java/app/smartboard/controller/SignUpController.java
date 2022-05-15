package app.smartboard.controller;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.*;
import app.smartboard.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpController extends BaseController {

    @FXML
    private Button cancelButton;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField password;


    private Stage stage;

    public SignUpController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }


    public void onSignUpButtonClick(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        // Create user profile
        int accountCreated;
        System.out.println("onSignUpButtonClick");

        Profile profile = new Profile();
        profile.setFirstName(firstName.getText().trim());
        profile.setLastName(lastName.getText().trim());

        accountCreated = this.model.getDatabaseHelper().createUser(username.getText().trim(), password.getText().trim(), profile);

        System.out.println(accountCreated);

        if (accountCreated == 1) {
            System.out.println("User created");

            User user = this.model.getDatabaseHelper().getUser(username.getText(), password.getText());
            ObservableList<Project> projects = FXCollections.observableArrayList();
            ObservableList<Tab> projectUI = FXCollections.observableArrayList();

            // Set the current user
            this.model.setCurrentUser(user);
            this.model.setProjects(projects);
            this.model.setProjectUI(projectUI);

            // Display Workspace view
            viewFactory.displayWorkspaceView();

            // Close Log In stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            viewFactory.closeStage(stage);

            // Set first name and profile image
            this.model.getWorkspaceViewModel().setUserFirstName(this.model.getCurrentUser().getProfile().getFirstName());
            this.model.getWorkspaceViewModel().setUserImage(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/default-profile-photo.png"))));
        }
    }

    public void onCancelButtonClick(ActionEvent event) throws IOException {

        System.out.println("onCancelButtonClick");
        // Display Sign Up view
        viewFactory.displayLoginView();

        // Close Log In stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);

    }
}
