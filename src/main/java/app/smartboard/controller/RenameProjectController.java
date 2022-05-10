package app.smartboard.controller;

import app.smartboard.model.BindDataHolder;
import app.smartboard.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RenameProjectController {
    Stage stage;
    @FXML
    public TextField projectNameTextField;
    @FXML
    public Label errorLabel;

    public void onConfirmButtonClick(ActionEvent event) {
        Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).setName(projectNameTextField.getText().trim());
        projectNameTextField.textProperty().bind(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).nameProperty());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
