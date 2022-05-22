package app.smartboard.controller;

import app.smartboard.model.Column;
import app.smartboard.model.Model;
import app.smartboard.model.Nameable;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateColumnController extends BaseController {

    private Stage stage;
    public TextField columnNameTextField;
    public Label errorLabel;

    public CreateColumnController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On Confirm button click
    public void onConfirmButtonClick(ActionEvent event) throws IOException {

        if (!validInput()) {
            this.errorLabel.setText("Enter a valid name");
        } else {

            // Create column
            Nameable nameable = this.model.createNameable("Column", this.columnNameTextField.getText().trim());
            this.model.getProjects().get(this.model.getProjectIndex()).addColumn((Column) nameable);


            // Create column VBox
            this.viewFactory.initializeColumn(nameable);

            // Close stage
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.viewFactory.closeStage(stage);
        }
    }

    // On Cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);

    }

    // Input validation
    private boolean validInput() {
        return !this.columnNameTextField.getText().isEmpty() && this.columnNameTextField.getText().length() <= 45;
    }
}
