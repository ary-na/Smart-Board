package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.model.Task;
import app.smartboard.view.ProjectView;
import app.smartboard.view.TaskState;
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
    public Hyperlink addDueDateHyperlink;
    public Label dueDateLabel;
    public Region dueDateRegion;
    public HBox dueDateHBox;
    private DatePicker datePicker;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Region dueDateHBoxRegion;
    private CheckBox completed;
    public TextArea taskDescriptionTextArea;
    public Label errorLabel;

    public EditTaskController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void initialize() {

        // Get selected task
        task = this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTask().get(this.model.getTaskIndex(this.model.getTaskViewModel().getTask()));

        // Get task name and set to text field
        this.taskNameTextField.setText(task.getName());

        // Get task due date and set value to date picker
        if (task.getDueDate() != null) {
            this.dueDateLabel.setText("Due date");
            HBox.setHgrow(this.dueDateRegion, Priority.ALWAYS);
            this.datePicker = new DatePicker(LocalDate.now());
            this.dueDateHBoxRegion = new Region();
            this.completed = new CheckBox("Complete");

            // Add children to HBox
            dueDateHBox.getChildren().addAll(
                    this.datePicker,
                    this.dueDateHBoxRegion,
                    this.completed
            );

            HBox.setHgrow(this.dueDateHBoxRegion, Priority.ALWAYS);
            addDueDateHyperlink.setText("Delete");
            this.datePicker.setValue(task.getDueDate());
        }

        // Get completed value and set to checkbox
        if(this.completed != null)
            this.completed.selectedProperty().set(task.getCompleted());

        // Get description
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

            if(this.completed != null)
                task.setCompleted(this.completed.selectedProperty().get());

            task.setDescription(this.taskDescriptionTextArea.getText());

            // Remove CSS class
            TaskState.removeCSSClass(taskDueDate);

            // Add CSS class
            taskDueDate.getStyleClass().add(TaskState.getCSSClass(this.viewFactory.getTaskState(task)));

            // Close stage
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.viewFactory.closeStage(stage);
        }
    }

    // On add due date hyperlink click
    public void onAddDueDateHyperlinkClick(ActionEvent event) {

        System.out.println("onAddDueDateHyperlinkClick");

        // Check due date HBox children on condition
        if (!this.dueDateHBox.getChildren().contains(this.datePicker)) {

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

            // Remove children from HBox
            dueDateHBox.getChildren().removeAll();

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
