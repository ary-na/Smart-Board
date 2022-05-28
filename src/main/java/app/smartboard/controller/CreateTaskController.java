package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.model.Nameable;
import app.smartboard.model.Task;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CreateTaskController extends BaseController {

    private Stage stage;
    public TextField taskNameTextField;
    public Hyperlink addDueDateHyperlink;
    public Label dueDateLabel;
    public Region dueDateRegion;
    public HBox dueDateHBox;
    private DatePicker datePicker;
    private Region dueDateHBoxRegion;
    private CheckBox completed;
    public TextArea taskDescriptionTextArea;
    public Label errorLabel;

    public CreateTaskController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On confirm button click
    public void onConfirmButtonClick(ActionEvent event) {

        // On invalid input condition
        if (!validInput()) {
            this.errorLabel.setText("Enter task name");
            this.taskNameTextField.requestFocus();
        } else {

            // Create task
            Nameable nameable = this.model.createNameable("Task", this.taskNameTextField.getText().trim());
            Task task = (Task) nameable;

            // Set due date
            if (datePicker != null)
                task.setDueDate(datePicker.getValue());

            // Set completed value
            if (this.completed != null)
                task.setCompleted(this.completed.selectedProperty().get());

            // Set description
            task.setDescription(this.taskDescriptionTextArea.getText());

            // Add task to list
            this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).addTask(task);

            // Create task VBox
            this.viewFactory.initializeTask(nameable);

            // Close stage
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.viewFactory.closeStage(stage);
        }

    }

    // On add due date hyperlink click
    public void onAddDueDateHyperlinkClick(ActionEvent event) {

        System.out.println("onAddDueDateHyperlinkClick");

        // Check due date HBox children on condition
        if (!this.dueDateHBox.getChildren().contains(this.datePicker) && !this.dueDateHBox.getChildren().contains(this.completed)) {

            this.dueDateLabel.setText("Due date");
            HBox.setHgrow(this.dueDateRegion, Priority.ALWAYS);

            this.datePicker = new DatePicker(LocalDate.now());
            this.dueDateHBoxRegion = new Region();

            HBox.setHgrow(this.dueDateHBoxRegion, Priority.ALWAYS);

            this.completed = new CheckBox("Complete");

            // Add children to HBox
            dueDateHBox.getChildren().addAll(
                    this.datePicker,
                    this.dueDateHBoxRegion,
                    this.completed
            );

            addDueDateHyperlink.setText("Delete");
        } else {
            this.dueDateLabel.setText(null);
            HBox.setHgrow(this.dueDateRegion, Priority.NEVER);
            this.datePicker.setValue(null);

            // Remove children from HBox
            dueDateHBox.getChildren().removeAll(
                    this.datePicker,
                    this.dueDateHBoxRegion,
                    this.completed
            );

            addDueDateHyperlink.setText("Add due date");
        }
    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);

    }

    // Input validation
    private boolean validInput() {
        return !this.taskNameTextField.getText().isEmpty() && this.taskNameTextField.getText().length() <= 45;
    }
}
