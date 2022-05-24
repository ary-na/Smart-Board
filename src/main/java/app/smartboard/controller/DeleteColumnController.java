package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ProjectView;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DeleteColumnController extends BaseController {

    private Stage stage;

    public DeleteColumnController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    // On delete button click
    public void onDeleteButtonClick(ActionEvent event) {

        System.out.println();
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names before deleting: " + column.getName()));
        System.out.println();

        // Delete column UI
        ProjectView projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
        projectView.getColumnViews().remove(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn()));

        // Delete column object
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().remove(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn()));

        System.out.println();
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names after deleting: " + column.getName()));
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
