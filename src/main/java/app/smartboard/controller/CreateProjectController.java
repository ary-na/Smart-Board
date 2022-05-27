package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProjectController extends BaseController {

    private Stage stage;
    public TextField projectNameTextField;
    public Label errorLabel;

    public CreateProjectController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On confirm button click
    public void onConfirmButtonClick(ActionEvent event) {

        // On invalid input condition
        if (!validInput()) {
            this.errorLabel.setText("Enter a valid name");
            this.projectNameTextField.requestFocus();
        } else {

            // Create project
            Nameable nameable = this.model.createNameable("Project", this.projectNameTextField.getText().trim());

            // Create project Tab
            this.viewFactory.initializeProject(nameable);

            // Close stage
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.viewFactory.closeStage(stage);

        }
    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);

    }

    // Input validation
    private boolean validInput() {
        return !this.projectNameTextField.getText().isEmpty() && this.projectNameTextField.getText().length() <= 45;
    }
}
