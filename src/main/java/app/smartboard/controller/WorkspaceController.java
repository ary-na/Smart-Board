package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ColumnView;
import app.smartboard.view.ProjectView;
import app.smartboard.view.TaskView;
import app.smartboard.view.ViewFactory;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import java.util.Map;
import java.util.Random;

public class WorkspaceController extends BaseController {

    private Stage stage;
    private ProjectView projectView;
    public TabPane tabPane;
    public MenuBar workspaceMenuBar;
    public MenuItem addColumnMenuItem;
    public MenuItem renameProjectMenuItem;
    public CheckMenuItem setDefaultProjectCheckMenuItem;
    public MenuItem deleteProjectMenuItem;
    public Label firstNameLabel;
    public ImageView profilePhotoImageView;
    public Label quoteLabel;

    public WorkspaceController(Model model, ViewFactory viewFactory, String fxml) {
        super(model, viewFactory, fxml);
    }

    public void initialize() {

        // Set tab pane to Workspace view model
        this.model.getProjectViewModel().setTabPane(this.tabPane);

        // Load user projects from database
        this.loadProjects();

        // Bind workspace content to workspace view model
        this.bindWorkspaceContent();

        // Load user profile data
        firstNameLabel.setText(this.model.getCurrentUser().getProfile().getFirstName());
        if (this.model.getCurrentUser().getProfile().getProfilePhoto() != null)
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(this.model.getCurrentUser().getProfile().getProfilePhoto())));

        // Display random quote
        this.displayRandomQuote();

        // Add event filter to column UI
        if (this.model.getProjects().size() > 0) {

            this.projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
            this.projectView.getColumnViews().forEach(column -> column.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
                this.columnEventFilter(column, mouseEvent);
            }));

        }
    }

    // On profile button click
    public void onProfileButtonClick(ActionEvent event) throws IOException {

        System.out.println("onProfileButtonClick");

        // Display Edit Profile view
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.displayEditProfileView(stage);

    }

    // On log out button click
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

    // On new project menu item click
    public void onNewProjectMenuItemClick(ActionEvent event) throws IOException {

        System.out.println("onNewProjectMenuItemClicked");

        // Display Create Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayCreateProjectView(stage);

    }

    // On add column menu item click
    public void onAddColumnMenuItemClick() throws IOException {

        // Display Create Project view
        this.stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayCreateColumnView(stage);

        initialize();

    }

    // On rename menu button click
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

    // On delete project menu item click
    public void onDeleteProjectMenuItemClick() throws IOException {

        System.out.println("onDeleteMenuItemClicked");

        // Set tab pane
        this.model.getProjectViewModel().setTabPane(this.tabPane);

        // Display Create Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayDeleteProjectView(stage);
    }

    // Load projects from database
    private void loadProjects() {

        // Load projects from database
        if (this.model.getProjects().size() > 0 && this.model.getProjectViewModel().getProjectTabs().size() == 0) {

            this.model.getProjects().forEach(project -> {
                this.viewFactory.initializeProject(project);
                project.getColumn().forEach(column -> {
                    this.viewFactory.initializeColumn(column);

                    ProjectView projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());
                    projectView.getColumnViews().forEach(columnUI -> columnUI.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
                        this.columnEventFilter(columnUI, mouseEvent);
                    }));

                    for (Map.Entry<ColumnView, Column> e : this.model.getColumnViewModel().getColumnMap().entrySet()) {
                        if (e.getValue() == column) {
                            this.model.getColumnViewModel().setColumn(e.getKey());
                            column.getTask().forEach(task -> {
                                this.viewFactory.initializeTask(task);
                            });
                        }
                    }
                });
            });
        }
    }

    // Bind workspace content to workspace view model
    private void bindWorkspaceContent() {

        // Disable menu buttons on condition
        if (this.model.getProjects().isEmpty() && this.model.getProjects() != null)
            this.model.getWorkspaceViewModel().setEmptyWorkspace(true);

        // Bind menu buttons disable property to workspace view model
        Bindings.bindBidirectional(addColumnMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(renameProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(deleteProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(setDefaultProjectCheckMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());

        // Bind user profile picture and first name to view model
        Bindings.bindContent(tabPane.getTabs(), this.model.getProjectViewModel().getProjectTabs());
        Bindings.bindBidirectional(firstNameLabel.textProperty(), this.model.getWorkspaceViewModel().userFirstNameProperty());
        Bindings.bindBidirectional(profilePhotoImageView.imageProperty(), this.model.getWorkspaceViewModel().userImageProperty());

    }

    // Display random quote from database
    private void displayRandomQuote() {

        // Select a random quote
        Random random = new Random();
        quoteLabel.setText(this.model.getDatabaseHelper().getQuote(random.nextInt(10) + 1));

    }

    // Add event filter to column UI
    private void columnEventFilter(VBox column, MouseEvent mouseEvent) {

        System.out.println("Column selected");

        // Set selected column
        this.model.getColumnViewModel().setColumn(column);
        ColumnView columnView = (ColumnView) column;

        // Add event filter to task UI
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

                // On add task button click
                button.setOnAction(e -> {
                    System.out.println("onAddTaskButtonClick");
                    try {
                        // Display Edit Profile view
                        this.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        viewFactory.displayCreateTaskView(stage);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                });

            }

            // Add actions to menu button menu items
            if (node instanceof MenuButton menuButton) {

                // On rename menu item click
                menuButton.getItems().get(0).setOnAction(e -> {

                    System.out.println("OnRenameMenuItemClick");

                    try {
                        // Display Rename column view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayRenameColumnView(stage);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }

                });

                // On delete menu item click
                menuButton.getItems().get(1).setOnAction(e -> {

                    System.out.println("OnDeleteMenuItemClick");

                    try {
                        // Display delete column view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayDeleteColumnView(stage);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                });

            }
        }
    }

    // Add event filter to task UI
    private void taskEventFilter(TaskView task, MouseEvent mouseEvent) {

        // Set selected task
        this.model.getTaskViewModel().setTask(task);

        // Task drag and drop
        this.taskDragAndDrop();

        // Get menu button
        VBox vBox = (VBox) mouseEvent.getSource();
        HBox hBox = (HBox) vBox.getChildren().get(0);
        VBox right = (VBox) hBox.getChildren().get(2);

        for (Node node : right.getChildren()) {
            // Add actions to menu button menu items
            if (node instanceof MenuButton menuButton) {

                // On edit menu item click
                menuButton.getItems().get(0).setOnAction(actionEvent -> {

                    System.out.println("OnEditMenuItemClick");
                    try {
                        // Display Edit task view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayEditTaskView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });

                // On delete menu item click
                menuButton.getItems().get(1).setOnAction(actionEvent -> {

                    System.out.println("OnDeleteMenuItemClick");
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


    // links to look for
    //https://docs.oracle.com/javafx/2/events/filters.htm
    //https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
    // http://www.java2s.com/Tutorials/Java/JavaFX_How_to/Shape/Handle_Shape_drag_and_drop_events.htm
    // https://examples.javacodegeeks.com/desktop-java/javafx/event-javafx/javafx-drag-drop-example/
    // https://stackoverflow.com/questions/68475291/javafx-finding-node-by-id
    // https://docs.oracle.com/javase/8/javafx/events-tutorial/paper-doll.htm#CBHFHJID
    // https://docs.oracle.com/javase/8/javafx/events-tutorial/paperdolljava.htm#BGBICFDH
    // https://stackoverflow.com/questions/12201712/how-to-find-an-element-with-an-id-in-javafx

    // Task drag and drop
    private void taskDragAndDrop() {

        // Source task
        VBox sourceTask = this.model.getTaskViewModel().getTask();

        // Target column
        VBox targetColumn = this.model.getColumnViewModel().getColumn();

        // Get project UI
        this.projectView = (ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex());

        // Source on drag detected
        sourceTask.setOnDragDetected(e -> {

            System.out.println("Source task name -> " + this.model.getTaskViewModel().getTaskMap().get(sourceTask).getName());

            // Start drag and drop
            Dragboard db = sourceTask.startDragAndDrop(TransferMode.MOVE);

            // Set task ID to clipboard
            ClipboardContent content = new ClipboardContent();
            content.putString(sourceTask.getId());
            db.setContent(content);

            e.consume();

        });


        // Target on drag over
        targetColumn.setOnDragOver(e -> {

            System.out.println("Target column name -> " + this.model.getColumnViewModel().getColumnMap().get(targetColumn).getName());

            // Accept transfer condition
            if (e.getGestureSource() != targetColumn && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }

            e.consume();

        });

        // Target on drag dropped
        targetColumn.setOnDragDropped(e -> {

            System.out.println("setOnDragDropped");

            // Get drag board
            Dragboard db = e.getDragboard();
            boolean success = false;

            if (db.hasString()) {

                success = true;

                // Get task ID from drag board
                Scene scene = targetColumn.getScene();
                VBox task = (VBox) scene.lookup("#" + db.getString());

                // Create dropped task object in target column
                Nameable nameable = this.model.getTaskViewModel().getTaskMap().get(task);
                this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(targetColumn)).addTask((Task) nameable);

                // Create dropped task UI object in target column
                int targetColumnViewIndex = projectView.getColumnViews().indexOf(targetColumn);
                this.viewFactory.moveTask(nameable, targetColumnViewIndex);

            }

            e.setDropCompleted(success);
            e.consume();

        });


        // Source on drag done
        sourceTask.setOnDragDone(e -> {

            System.out.println("setOnDragDone");

            // Successful drag done condition
            if (e.getTransferMode() == TransferMode.MOVE) {

                VBox taskContainer = (VBox) sourceTask.getParent();
                VBox columnVBox = (VBox) taskContainer.getParent();

                // Delete source task UI
                ColumnView columnView = ((ProjectView) this.model.getProjectViewModel().getProjectTabs().get(model.getProjectIndex())).getColumnViews().get(this.model.getColumnIndex(this.model.getColumnViewModel().getColumn()));
                columnView.getTaskViews().remove(this.model.getTaskIndex(sourceTask));

                // Delete source task object
                this.model.getProjects().get(this.model.getProjectIndex()).getColumn().get(this.model.getColumnIndex(columnVBox)).getTask().remove(this.model.getTaskIndex(sourceTask));
            }

            e.consume();

        });

    }
}