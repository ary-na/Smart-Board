package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RenameProjectController extends BaseController {
    Stage stage;
    @FXML
    public TextField projectNameTextField;
    @FXML
    public Label errorLabel;

    public RenameProjectController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onConfirmButtonClick(ActionEvent event) {

        int index = this.model.getProjectViewModel().getProjectTabs().indexOf(this.model.getProjectViewModel().getTabPane().getSelectionModel().getSelectedItem());

        System.out.println();
        this.model.getProjects().forEach(project -> System.out.println("Project names before renaming: " + project.getName()));
        System.out.println();

        // Rename project object
        this.model.getProjects().get(index).setName(projectNameTextField.getText().trim());

        // Rename project UI
        this.model.getProjectViewModel().getProjectTabs().get(index).textProperty().set(projectNameTextField.getText().trim());

        System.out.println();
        this.model.getProjects().forEach(project -> System.out.println("Project names after deletion: " + project.getName()));
        System.out.println();

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);

    }
}
