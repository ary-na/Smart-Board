package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ColumnView;
import app.smartboard.view.ProjectView;
import app.smartboard.view.TaskView;
import app.smartboard.view.ViewFactory;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/*
links to add
https://stackoverflow.com/questions/16977100/how-do-i-add-margin-to-a-javafx-element-using-css
https://stackoverflow.com/questions/11127999/how-do-you-set-the-style-for-a-javafx-contextmenu-using-css
https://stackoverflow.com/questions/26831978/javafx-datepicker-getvalue-in-a-specific-format
https://jenkov.com/tutorials/javafx/datepicker.html
https://stackoverflow.com/questions/31569299/how-to-extend-custom-javafx-components-that-use-fxml
https://jenkov.com/tutorials/javafx/checkbox.html
https://www.educba.com/sql-delete-row/
 */

public class WorkspaceController extends BaseController {

    public TabPane projectTabPane;
    private HBox projectHBox;
    private Stage stage;

    public MenuBar workspaceMenuBar;
    public Menu projectMenu;
    public MenuItem addColumnMenuItem;
    public MenuItem renameProjectMenuItem;
    public CheckMenuItem setDefaultProjectCheckMenuItem;
    public MenuItem deleteProjectMenuItem;
    public ImageView profilePhotoImageView;
    public Label firstNameLabel;
    public TabPane tabPane;
    public Label quoteLabel;

    public WorkspaceController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void initialize() {

        // Set tab pane to Workspace view model
        this.model.getProjectViewModel().setTabPane(this.tabPane);

        if (this.model.getProjects().isEmpty() && this.model.getProjects() != null)
            this.model.getWorkspaceViewModel().setEmptyWorkspace(true);

        this.bindWorkspaceContent();

        // Load user data
        firstNameLabel.setText(this.model.getCurrentUser().getProfile().getFirstName());
        if (this.model.getCurrentUser().getProfile().getProfilePhoto() != null)
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(this.model.getCurrentUser().getProfile().getProfilePhoto())));

        this.displayRandomQuote();

        if (this.model.getProjectViewModel().getProjectTabs().size() > 0) {
            ProjectView projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
            projectView.getColumnViews().forEach(column -> column.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
                this.columnEventFilter(column, mouseEvent);
            }));
        }
    }

    public void onProfileButtonClick(ActionEvent event) throws IOException {

        System.out.println("onProfileButtonClick");
        // Display Edit Profile view
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.displayEditProfileView(stage);

    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException, SQLException {

        System.out.println("onLogOutButtonClick");
        // Display Sign Up view
        viewFactory.displayLoginView();

        // Save user projects
        this.model.getDatabaseHelper().setProjects(this.model.getCurrentUser().getUsername(), this.model.getProjects());

        // Reset user data
        this.model.getWorkspaceViewModel().setUserImage(null);
        this.model.getWorkspaceViewModel().setUserFirstName(null);

        // Reset model
        this.model = new Model();

        // Close Workspace stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public void onNewProjectMenuItemClick(ActionEvent event) throws IOException {

        System.out.println("onNewProjectMenuItemClicked");

        // Display Create Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayCreateProjectView(stage);
    }

    public void onAddColumnMenuItemClick() throws IOException {

        // Display Create Project view
        this.stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayCreateColumnView(stage);
        initialize();

    }

    public void onRenameMenuItemClick(ActionEvent event) throws IOException {

        System.out.println("onRenameMenuItemClicked");

        // Display Rename Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayRenameProjectView(stage);
    }


    // On set as default check menu item click
    public void onSetAsDefaultCheckMenuItemClick(ActionEvent event) {

        // On check menu item selected condition
        if (setDefaultProjectCheckMenuItem.isSelected()) {

            // Set default true
            this.model.getProjects().get(this.model.getProjectIndex()).setIsDefault(true);
            // Add default style class
            this.model.getProjectViewModel().getProjectTabs().get(getModel().getProjectIndex()).getStyleClass().add("default-project");

        } else {

            // For each project set default false
            this.model.getProjects().forEach(project -> {

                project.setIsDefault(false);

                // For each project tab remove default style class
                this.model.getProjectViewModel().getProjectTabs().forEach(tab -> {
                    tab.getStyleClass().remove("default-project");
                });

            });

        }
    }


    public void onDeleteProjectMenuItemClick() throws IOException {

        System.out.println("onDeleteMenuItemClicked");

        // Set tab pane
        this.model.getProjectViewModel().setTabPane(this.tabPane);

        // Display Create Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayDeleteProjectView(stage);
    }

    private void bindWorkspaceContent() {

        Bindings.bindBidirectional(addColumnMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(renameProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(deleteProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(setDefaultProjectCheckMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());

        // Bind user profile picture and first name to view model
        Bindings.bindBidirectional(profilePhotoImageView.imageProperty(), this.model.getWorkspaceViewModel().userImageProperty());

        Bindings.bindBidirectional(firstNameLabel.textProperty(), this.model.getWorkspaceViewModel().userFirstNameProperty());
        Bindings.bindContent(tabPane.getTabs(), this.model.getProjectViewModel().getProjectTabs());

    }

    private void displayRandomQuote() {

        // Select a random quote
        Random random = new Random();
        quoteLabel.setText(this.model.getDatabaseHelper().getQuote(random.nextInt(10) + 1));

    }

    private void columnEventFilter(VBox column, MouseEvent mouseEvent) {

        // Set selected column
        this.model.getColumnViewModel().setColumn(column);
        ColumnView columnView = (ColumnView) column;

        columnView.getTaskViews().forEach(task -> task.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            this.taskEventFilter(task, e);
        }));

        // Get column header
        VBox vBox = (VBox) mouseEvent.getSource();
        HBox hBox = (HBox) vBox.getChildren().get(0);

        // Get column header children
        for (Node node : hBox.getChildren()) {

            // Add action to add task button
            if (node instanceof Button button) {
                button.setOnAction(actionEvent -> {
                    System.out.println("onAddTaskButtonClicked");
                    try {
                        // Display Edit Profile view
                        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        viewFactory.displayCreateTaskView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            // Add actions to menu button menu items
            if (node instanceof MenuButton menuButton) {

                menuButton.getItems().get(0).setOnAction(actionEvent -> {
                    System.out.println("OnRenameMenuItemClicked");
                    try {
                        // Display Rename column view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayRenameColumnView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                menuButton.getItems().get(1).setOnAction(actionEvent -> {
                    System.out.println("OnDeleteMenuItemClicked");
                    try {
                        // Display delete column view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayDeleteColumnView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }
        }
    }

    private void taskEventFilter(TaskView task, MouseEvent mouseEvent) {

        // Set selected task
        this.model.getTaskViewModel().setTask(task);

        // Get menu button
        VBox vBox = (VBox) mouseEvent.getSource();
        HBox hBox = (HBox) vBox.getChildren().get(0);
        VBox right = (VBox) hBox.getChildren().get(2);

        for (Node node : right.getChildren()) {
            // Add actions to menu button menu items
            if (node instanceof MenuButton menuButton) {

                menuButton.getItems().get(0).setOnAction(actionEvent -> {
                    System.out.println("OnEditMenuItemClicked");
                    try {
                        // Display Edit task view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayEditTaskView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                menuButton.getItems().get(1).setOnAction(actionEvent -> {
                    System.out.println("OnDeleteMenuItemClicked");
                    try {
                        // Display delete task view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayDeleteTaskView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }

        }
    }
}