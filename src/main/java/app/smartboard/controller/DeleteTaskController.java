package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ColumnView;
import app.smartboard.view.ProjectView;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DeleteTaskController extends BaseController{

    private Stage stage;

    public DeleteTaskController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On delete button click
    public void onDeleteButtonClick(ActionEvent event) {

        System.out.println();
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTask().forEach(task -> System.out.println("Task names before deleting: " + task.getName()));
        System.out.println();

        // Delete task UI
        ColumnView columnView = ((ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex())).getColumnViews().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn()));
        columnView.getTaskViews().remove(this.model.getTaskIndex(this.model.getTaskViewModel().getTask()));

        // Delete task object
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTask().remove(this.model.getTaskIndex(this.model.getTaskViewModel().getTask()));

        System.out.println();
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getTask().forEach(task -> System.out.println("Task names after deleting: " + task.getName()));
        System.out.println();

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
}
