package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ViewProjectFactory;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProjectController extends BaseController {

    private Stage stage;
    @FXML
    public TextField projectNameTextField;
    @FXML
    public Label errorLabel;

    public CreateProjectController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onConfirmButtonClick(ActionEvent event) {

        if (!validInput()) {
            errorLabel.setText("Enter a valid name");
        } else {

            // Create project
            NameableFactory nameableFactory = new NameableFactory();
            Nameable nameable = nameableFactory.createNameable("Project", projectNameTextField.getText().trim());
            this.model.getProjects().add(nameable);

            // Add tab to the arraylist
            this.model.getProjectUI().add(new ViewProjectFactory(nameable));
            this.model.getViewModel().setProjectCreated(false);

            // Close stage
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.viewFactory.closeStage(stage);
        }
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean validInput() {
        return !projectNameTextField.getText().isEmpty() && projectNameTextField.getText().length() <= 45;
    }
}
