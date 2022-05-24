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

    // On delete button click
    public void onDeleteButtonClick(ActionEvent event) {

        System.out.println();
        this.model.getProjects().forEach(project -> System.out.println("Projects before deletion: " + project.getName()));
        System.out.println();

        int index = this.model.getProjectViewModel().getProjectTabs().indexOf(this.model.getProjectViewModel().getTabPane().getSelectionModel().getSelectedItem());

        // Delete project UI
        this.model.getProjectViewModel().getProjectTabs().remove(this.model.getProjectIndex());

        // Select a tab
        if(!(index - 1 < 0))
            this.model.getProjectViewModel().getTabPane().getSelectionModel().select(index - 1);
        else
            this.model.getProjectViewModel().getTabPane().getSelectionModel().select(index);

        // Delete project object
        this.model.getProjects().remove(this.model.getProjectIndex());

        System.out.println();
        this.model.getProjects().forEach(project -> System.out.println("Projects after deletion: " + project.getName()));
        System.out.println();

        // Disable menu button condition true
        if (this.model.getProjects().isEmpty())
            this.model.getWorkspaceViewModel().setEmptyWorkspace(true);

        // Close stage
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);
    }

    // On cancel button click
    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.viewFactory.closeStage(stage);

    }
}
