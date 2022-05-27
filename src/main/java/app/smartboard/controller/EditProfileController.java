package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class EditProfileController extends BaseController {

    private Stage stage;
    public Label firstNameLabel;
    public ImageView profilePhotoImageView;
    private File selectedFile;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public Label errorLabel;

    public EditProfileController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void initialize() {

        // Set user data to scene
        firstNameLabel.setText(this.model.getCurrentUser().getProfile().getFirstName());
        firstNameTextField.setText(this.model.getCurrentUser().getProfile().getFirstName());
        lastNameTextField.setText(this.model.getCurrentUser().getProfile().getLastName());
        if (this.model.getCurrentUser().getProfile().getProfilePhoto() != null) {
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(this.model.getCurrentUser().getProfile().getProfilePhoto())));
        }
    }

    // On confirm button click
    public void onConfirmButtonClick(ActionEvent event) throws SQLException, IOException {

        // On invalid input condition
        if (!validInput()) {
            this.errorLabel.setText("Enter your name and last name");
            this.firstNameTextField.requestFocus();
        } else {

            // Set new first name and last name
            this.model.getCurrentUser().getProfile().setFirstName(firstNameTextField.getText().trim());
            this.model.getCurrentUser().getProfile().setLastName(lastNameTextField.getText().trim());

            // Set new user image
            if (selectedFile != null) {
                Path path = selectedFile.toPath();
                this.model.getCurrentUser().getProfile().setProfilePhoto(Files.readAllBytes(path));
            }

            // Update database
            this.model.getDatabaseHelper().updateProfile(this.model.getCurrentUser().getUsername(), this.model.getCurrentUser().getProfile());

            // Close stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            viewFactory.closeStage(stage);

            // Set first name and profile image
            this.model.getWorkspaceViewModel().setUserFirstName(this.model.getCurrentUser().getProfile().getFirstName());
            this.model.getWorkspaceViewModel().setUserImage(new Image(new ByteArrayInputStream(this.model.getCurrentUser().getProfile().getProfilePhoto())));
        }
    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    // On image view button click
    public void onImageViewButtonClicked(ActionEvent event) throws IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Display file chooser and get the file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG, JPG, JPEG", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        selectedFile = fileChooser.showOpenDialog(stage);

        // Set selected file to user profile on condition
        if (selectedFile != null) {
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            profilePhotoImageView.setImage(new Image(fileInputStream));
        }
    }

    // Input validation
    private boolean validInput() {
        return (!this.firstNameTextField.getText().isEmpty() && this.firstNameTextField.getText().length() <= 45) && (!this.lastNameTextField.getText().isEmpty() && this.lastNameTextField.getText().length() <= 45);
    }
}
