package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ProjectView;
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
import java.util.Random;

public class WorkspaceController extends BaseController {

    public TabPane projectTabPane;
    private HBox projectHBox;
    private Stage stage;

    @FXML
    private MenuBar workspaceMenuBar;
    @FXML
    private Menu projectMenu;
    @FXML
    private MenuItem addColumnMenuItem;
    @FXML
    private MenuItem renameProjectMenuItem;
    @FXML
    private MenuItem setDefaultProjectMenuItem;
    @FXML
    private MenuItem deleteProjectMenuItem;
    @FXML
    private ImageView profilePhotoImageView;
    @FXML
    private Label firstNameLabel;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label quoteLabel;

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

    public void onLogOutButtonClick(ActionEvent event) throws IOException {

        System.out.println("onLogOutButtonClick");
        // Display Sign Up view
        viewFactory.displayLoginView();
        this.model.getWorkspaceViewModel().setUserImage(null);
        this.model.getWorkspaceViewModel().setUserFirstName(null);


        this.model = new Model();


        // Close Workspace stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public void onNewProjectMenuItemClicked(ActionEvent event) throws IOException {

        System.out.println("onNewProjectMenuItemClicked");

        // Display Create Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayCreateProjectView(stage);
    }

    public void onAddColumnMenuItemClicked() throws IOException {

        // Display Create Project view
        this.stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayCreateColumnView(stage);
        initialize();

        // new links
        //https://jenkov.com/tutorials/javafx/menubutton.html
        //https://localcoder.org/how-to-determine-if-the-user-clicked-outside-a-particular-javafx-node
        //https://stackoverflow.com/questions/25930944/javafx-dynamically-add-buttons
        //https://stackoverflow.com/questions/9816568/how-can-i-get-a-buttons-text-in-javafx-if-the-button-is-being-read-as-a-node-l#9835566
        //https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx
        //https://stackoverflow.com/questions/51594560/javafx-textarea-style-with-css
        //https://stackoverflow.com/questions/30210117/remove-arrow-on-javafx-menubutton
        //https://jenkov.com/tutorials/javafx/menubutton.html

    }

    public void onRenameMenuItemClicked(ActionEvent event) throws IOException {

        System.out.println("onRenameMenuItemClicked");

        // Display Rename Project view
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        viewFactory.displayRenameProjectView(stage);
    }

    public void onSetAsDefaultMenuItemClicked(ActionEvent event) {
        System.out.println("Set project as default");
    }

    public void onDeleteProjectMenuItemClicked() throws IOException {

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
        Bindings.bindBidirectional(setDefaultProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());

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
                        // Display Edit Profile view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayRenameColumnView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                menuButton.getItems().get(1).setOnAction(actionEvent -> {
                    System.out.println("OnDeleteMenuItemClicked");
                    try {
                        // Display Edit Profile view
                        this.stage = (Stage) menuButton.getScene().getWindow();
                        viewFactory.displayDeleteColumnView(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }
        }
    }
}