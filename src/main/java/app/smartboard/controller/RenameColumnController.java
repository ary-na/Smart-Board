package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class RenameColumnController extends BaseController {
    Stage stage;
    @FXML
    public TextField columnNameTextField;
    @FXML
    public Label errorLabel;

    public RenameColumnController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onConfirmButtonClick(ActionEvent event) {

        // Rename column
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names before renaming: " + column.getName()));

        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).setName(columnNameTextField.getText().trim());
        HBox columnHeader = (HBox) this.model.getColumnViewModel().getColumnVBoxes().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getChildren().get(0);
        Label label = (Label) columnHeader.getChildren().get(0);
        label.textProperty().set(columnNameTextField.getText().trim());


        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names after renaming: " + column.getName()));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
