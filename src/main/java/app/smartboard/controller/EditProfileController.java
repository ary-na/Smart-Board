package app.smartboard.controller;

import app.smartboard.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class EditProfileController {

    private Stage stage;
    @FXML
    public Label firstNameLabel;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    public ImageView profilePhotoImageView;
    @FXML
    public Button imageViewButton;

    @FXML
    public void initialize() {

        firstNameLabel.setText(Model.getModelInstance().getCurrentUser().getProfile().getFirstName());
        firstNameTextField.setText(Model.getModelInstance().getCurrentUser().getProfile().getFirstName());
        lastNameTextField.setText(Model.getModelInstance().getCurrentUser().getProfile().getLastName());
        if (Model.getModelInstance().getCurrentUser().getProfile().getProfilePhoto() != null) {
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(Model.getModelInstance().getCurrentUser().getProfile().getProfilePhoto())));
        }
    }

    @FXML
    public void onConfirmButtonClick(ActionEvent event) throws SQLException, IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Model.getModelInstance().getCurrentUser().getProfile().setFirstName(firstNameTextField.getText());
        Model.getModelInstance().getCurrentUser().getProfile().setLastName(lastNameTextField.getText());

        Model.getModelInstance().getDatabaseHelper().updateProfile(Model.getModelInstance().getCurrentUser().getUsername(), Model.getModelInstance().getCurrentUser().getProfile());

        stage.close();
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onImageViewButtonClicked(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG, JPG, JPEG", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);
        FileInputStream fileInputStream = new FileInputStream(selectedFile);
        profilePhotoImageView.setImage(new Image(fileInputStream));
        Path path = selectedFile.toPath();
        Model.getModelInstance().getCurrentUser().getProfile().setProfilePhoto(Files.readAllBytes(path));
    }
}
