package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.model.Task;
import app.smartboard.view.ProjectView;
import app.smartboard.view.TaskView;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditTaskController extends BaseController {

    private Stage stage;
    private Task task;
    public TextField taskNameTextField;
    public VBox dueDateVBox;
    public Hyperlink addDueDateHyperlink;
    public Label dueDateLabel;
    public Region dueDateRegion;
    private DatePicker datePicker;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public TextArea taskDescriptionTextArea;
    public Label errorLabel;

    public EditTaskController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void initialize() {

        // Get selected task
        task = this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTask().get(this.model.getTaskIndex(this.model.getTaskViewModel().getTask()));

        // Set task data to scene
        this.taskNameTextField.setText(task.getName());
        if (task.getDueDate() != null) {
            this.dueDateLabel.setText("Due date");
            HBox.setHgrow(this.dueDateRegion, Priority.ALWAYS);
            this.datePicker = new DatePicker(LocalDate.now());
            dueDateVBox.getChildren().add(datePicker);
            addDueDateHyperlink.setText("Delete");
            this.datePicker.setValue(task.getDueDate());
        }
        this.taskDescriptionTextArea.setText(task.getDescription());

    }

    // On confirm button click
    public void onConfirmButtonClick(ActionEvent event) {

        // On invalid input condition
        if (!validInput()) {
            this.errorLabel.setText("Enter task name");
            this.taskNameTextField.requestFocus();
        } else {

            // Get task UI
            TaskView taskView = ((ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex())).getColumnViews().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTaskViews().get(this.model.getTaskIndex(this.model.getTaskViewModel().getTask()));
            HBox taskContainer = (HBox) taskView.getChildren().get(0);
            VBox left = (VBox) taskContainer.getChildren().get(0);
            Label taskTitle = (Label) left.getChildren().get(0);
            Label taskDueDate = (Label) left.getChildren().get(1);

            // Edit task UI
            taskTitle.setText(this.taskNameTextField.getText().trim());
            if (this.datePicker != null)
                taskDueDate.setText(dateTimeFormatter.format(this.datePicker.getValue()));


            // Edit task object
            task.setName(this.taskNameTextField.getText().trim());
            if (datePicker != null)
                task.setDueDate(datePicker.getValue());
            task.setDescription(this.taskDescriptionTextArea.getText());


            // Close stage
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.viewFactory.closeStage(stage);
        }
    }

    // On add due date hyperlink click
    public void onAddDueDateHyperlinkClick(ActionEvent event) {

        System.out.println("onAddDueDateHyperlinkClick");

        // Get due date VBox children condition
        if (!this.dueDateVBox.getChildren().contains(this.datePicker)) {
            this.dueDateLabel.setText("Due date");
            HBox.setHgrow(this.dueDateRegion, Priority.ALWAYS);
            this.datePicker = new DatePicker(LocalDate.now());
            dueDateVBox.getChildren().add(datePicker);
            addDueDateHyperlink.setText("Delete");
        } else {
            this.dueDateLabel.setText(null);
            HBox.setHgrow(this.dueDateRegion, Priority.NEVER);
            dueDateVBox.getChildren().remove(datePicker);
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
