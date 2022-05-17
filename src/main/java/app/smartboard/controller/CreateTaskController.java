package app.smartboard.controller;

import app.smartboard.model.Column;
import app.smartboard.model.Model;
import app.smartboard.model.Nameable;
import app.smartboard.model.Task;
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

        // Create column
        Nameable nameable = this.model.createNameable("Task", this.taskNameTextField.getText().trim());
        int index = this.model.getProjectViewModel().getTabPane().getSelectionModel().getSelectedIndex();
        this.model.getProjects().get(index).getColumn().getLast().addTask((Task) nameable);


        // Create column VBox
        this.viewFactory.initializeTask(nameable);

        // Close stage
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);
    }
}
