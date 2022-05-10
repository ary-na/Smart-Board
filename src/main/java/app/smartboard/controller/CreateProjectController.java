package app.smartboard.controller;

import app.smartboard.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProjectController {

    private Stage stage;
    @FXML
    public TextField projectNameTextField;
    @FXML
    public Label errorLabel;

    public void onConfirmButtonClick(ActionEvent event) {

        if (projectNameTextField.getText().isBlank() || projectNameTextField.getText().length() > 45) {
            errorLabel.setText("Enter a valid name");
        } else {

            // Create a project
            NameableFactory nameableFactory = new NameableFactory();
            Nameable nameable = nameableFactory.createNameable("Project", projectNameTextField.getText().trim());
            Model.getModelInstance().getProjects().add((Project) nameable);
            projectNameTextField.textProperty().bindBidirectional(nameable.nameProperty());
            ProjectUIAdapter projectUIAdapter = new ProjectUIAdapter((Project) nameable);

            Model.getModelInstance().getProjectUI().add(projectUIAdapter);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
