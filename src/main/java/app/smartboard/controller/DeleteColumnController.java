package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DeleteColumnController extends BaseController {

    private Stage stage;

    public DeleteColumnController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onDeleteButtonClick(ActionEvent event) {

        // Delete project
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names before deleting: " + column.getName()));

        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().remove(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn()));
        this.model.getColumnViewModel().getColumnVBoxes().remove(this.model.getColumnViewModel().getColumn());


        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names after deleting: " + column.getName()));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
