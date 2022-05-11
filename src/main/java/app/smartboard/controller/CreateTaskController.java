package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateTaskController extends BaseController {
    private Stage stage;
    @FXML
    public Label errorLabel;
    @FXML
    public TextField taskNameTextField;

    public CreateTaskController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onConfirmButtonClick(ActionEvent event) {
        //Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).getColumn().get(BindDataHolder.getBindDataHolderInstance().getColumnIndex()).addTask(new Task(taskNameTextField.getText().trim()));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
