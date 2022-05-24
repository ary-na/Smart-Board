package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ProjectView;
import app.smartboard.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class RenameColumnController extends BaseController {

    private Stage stage;
    public TextField columnNameTextField;
    public Label errorLabel;

    public RenameColumnController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void onConfirmButtonClick(ActionEvent event) {


        System.out.println();
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names before renaming: " + column.getName()));
        System.out.println();

        // Rename column UI
        ProjectView projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
        HBox columnHeader = (HBox) projectView.getColumnViews().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).getChildren().get(0);
        Label label = (Label) columnHeader.getChildren().get(0);
        label.textProperty().set(columnNameTextField.getText().trim());

        // Rename column object
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn())).setName(columnNameTextField.getText().trim());

        System.out.println();
        this.model.getProjects().get(this.model.getProjectIndex()).getColumn().forEach(column -> System.out.println("Column names after renaming: " + column.getName()));
        System.out.println();

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public void onCancelButtonClick(ActionEvent event) {

        // Close stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);

    }
}
