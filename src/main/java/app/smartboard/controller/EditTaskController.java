package app.smartboard.controller;

import app.smartboard.model.Column;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditTaskController extends BaseController {

    private Stage stage;
    public Label errorLabel;
    public Label taskNameLabel;
    public TextField taskNameTextField;
    public TextArea taskDescriptionTextArea;
    public VBox dueDateVBox;
    public HBox dueDateHBox;
    public Label dueDateLabel;
    public Label checklistLabel;
    public Region dueDateRegion;
    public Region checklistRegion;
    public VBox checklistVBox;
    public Hyperlink addDueDateHyperlink;
    public Hyperlink addChecklistHyperlink;
    private DatePicker datePicker;
    private CheckBox checkBox;

    public EditTaskController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void initialize() {

        Task task = this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTask().get(this.model.getTaskIndex(this.model.getTaskViewModel().getTask()));
        this.taskNameTextField.setText(task.getName());
        if(task.getDueDate() != null){
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

//        // Create column
//        Nameable nameable = this.model.createNameable("Task", this.taskNameTextField.getText().trim());
//        Task task = (Task) nameable;
//        task.setDueDate(datePicker.getValue());
//        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).addTask(task);
//
//        // Create column VBox
//        this.viewFactory.initializeTask(nameable);

        // Close stage
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);

    }

    public void onAddDueDateHyperlinkClick(ActionEvent event){
//        System.out.println("onAddDueDateHyperlinkClick");
//
//        if(!this.dueDateVBox.getChildren().contains(this.datePicker)) {
//            this.dueDateLabel.setText("Due date");
//            HBox.setHgrow(this.dueDateRegion, Priority.ALWAYS);
//            this.datePicker = new DatePicker(LocalDate.now());
//            dueDateVBox.getChildren().add(datePicker);
//            addDueDateHyperlink.setText("Delete");
//        }
//        else{
//            this.dueDateLabel.setText(null);
//            HBox.setHgrow(this.dueDateRegion, Priority.NEVER);
//            dueDateVBox.getChildren().remove(datePicker);
//            addDueDateHyperlink.setText("Add due date");
//        }
    }

    public void onAddChecklistHyperlinkClick(ActionEvent event){
//        System.out.println("onAddChecklistHyperlinkClick");
//
//        this.checklistLabel.setText("Checklist");
//        HBox.setHgrow(this.checklistRegion, Priority.ALWAYS);
//        this.addChecklistHyperlink.setText("Delete");

    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);

    }
}
