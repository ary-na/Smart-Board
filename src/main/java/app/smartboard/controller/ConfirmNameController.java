package app.smartboard.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfirmNameController {

    @FXML
    public TextField projectNameTextField;
    @FXML
    public Button cancelButton;
    @FXML
    private Label nameLabel;

    public void initialize(){
        nameLabel.setText("Enter project name:");
    }


    public void onConfirmButtonClick(ActionEvent actionEvent) {
        projectNameTextField.getText();
    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
