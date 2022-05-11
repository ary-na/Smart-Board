package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DeleteProjectController extends BaseController {

    private Stage stage;

    public DeleteProjectController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onDeleteButtonClick(ActionEvent event) {

        // Delete project
        int index = this.model.getProjectUI().indexOf(this.model.getViewModel().getTabPane().getSelectionModel().getSelectedItem());

        this.model.getProjectUI().remove(index);

        this.model.getProjects().forEach(project -> System.out.println("Projects before deletion: " + project.getName()));

        this.model.getProjects().remove(index);

        this.model.getProjects().forEach(project -> System.out.println("Projects after deletion: " + project.getName()));

        if (this.model.getProjects().isEmpty())
            this.model.getViewModel().setProjectCreated(true);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
