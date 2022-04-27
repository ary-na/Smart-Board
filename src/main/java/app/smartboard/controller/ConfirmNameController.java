package app.smartboard.controller;

import app.smartboard.model.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmNameController {

    private Stage stage;
    @FXML
    public TextField projectNameTextField;
    @FXML
    public Button cancelButton;
    @FXML
    public Button confirmButton;
    @FXML
    private Label nameLabel;

    public void initialize() {
        nameLabel.setText("Project name");
    }

    public void onConfirmButtonClick(ActionEvent actionEvent) throws IOException {
        ControllerHelper.getControllerHelperInstance().setName(projectNameTextField.getText());
        stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
