package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateColumnController extends BaseController {

    Stage stage;
    @FXML
    public TextField columnNameTextField;
    @FXML
    public Label errorLabel;

    public CreateColumnController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onConfirmButtonClick(ActionEvent event) {
        //Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).addColumn(new Column(columnNameTextField.getText().trim()));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
