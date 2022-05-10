package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ViewFactory;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class WorkspaceController extends BaseController {

    public TabPane projectTabPane;
    @FXML
    private HBox projectHBox;
    private Stage stage;
    private final LinkedList<Tab> projectTabs = new LinkedList<>();

    @FXML
    private MenuBar workspaceMenuBar;
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

        // Bind user profile picture and first name to view model
        Bindings.bindBidirectional(profilePhotoImageView.imageProperty(), this.model.getViewModel().imageProperty());
        Bindings.bindBidirectional(firstNameLabel.textProperty(), this.model.getViewModel().userFirstNameProperty());


        // Load user data
        if (this.model.getCurrentUser().getProfile().getProfilePhoto() != null) {
            firstNameLabel.setText(this.model.getCurrentUser().getProfile().getFirstName());
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(this.model.getCurrentUser().getProfile().getProfilePhoto())));
        }

        // Select a random quote
        Random random = new Random();
        quoteLabel.setText(this.model.getDatabaseHelper().getQuote(random.nextInt(10) + 1));
    }

    public void onProfileButtonClick(ActionEvent event) throws IOException {

        System.out.println("onProfileButtonClick");
        // Display Sign Up view
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.displayEditProfileView(stage);

    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {

        System.out.println("onLogOutButtonClick");
        // Display Sign Up view
        viewFactory.displayLoginView();

        // Close Log In stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public void onNewProjectMenuItemClicked(ActionEvent event) throws IOException {

        // Create stage -> create new project
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Create Project", "view/create-project-view.fxml");

        // Create project UI
        tabPane.getSelectionModel().selectLast();

        /*
        scrollpane
        https://stackoverflow.com/questions/50184756/remove-border-around-javafx-scrollpane-scroll-bar-when-not-focused
        https://stackoverflow.com/questions/42808539/in-javafx-how-to-change-scrollbar-arrow-color
        https://stackoverflow.com/questions/41804373/javafx-scrollpane-styling
        https://stackoverflow.com/questions/16977100/how-do-i-add-margin-to-a-javafx-element-using-css
        https://stackoverflow.com/questions/20118574/set-background-color-in-scrollpane
        https://stackoverflow.com/questions/39962796/creating-map-composed-of-2-lists-using-stream-collect-in-java


        https://github.com/dipto-pratyaksa-rmit/javafx-mvc/blob/main/src/main/java/com/javafx/mvc/model/Person.java
        https://stackoverflow.com/questions/21865044/javafx-sethgrow-doesnt-work
        https://stackoverflow.com/questions/34211310/remove-the-glowing-border-from-focused-tab-with-css
        https://stackoverflow.com/questions/17091605/how-to-change-the-tab-pane-style
        https://stackoverflow.com/questions/41642403/how-to-add-css-to-a-javafx-element

        https://github.com/adwansyed/Kanban-Board/tree/master/src/main/java/project

        https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/968af113fc73cc16454d26ab95db0fa5962b7a34
        https://www.udemy.com/course/advanced-programming-with-javafx-build-an-email-client/learn/lecture/16121875#questions
         */
    }

    public void onAddColumnMenuItemClicked(ActionEvent event) throws IOException {
        // Get tab index
//        BindDataHolder.getBindDataHolderInstance().setTabIndex(tabPane.getSelectionModel().getSelectedIndex());
//
//        // Open create new column stage
//        stage = (Stage) workspaceMenuBar.getScene().getWindow();
//        StageHelper.getStageHelperInstance().createChildStage(stage, "Create Column", "view/create-column-view.fxml");
//
//        // Create column UI
//        ProjectUIAdapter projectUIAdapter = (ProjectUIAdapter) projectTabs.get(BindDataHolder.getBindDataHolderInstance().getTabIndex());
//        projectUIAdapter.setColumns(new ColumnUIAdapter(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()), projectTabs.get(BindDataHolder.getBindDataHolderInstance().getTabIndex())));
//
//        projectUIAdapter.getColumns().getLast().getCreateTaskButton().setOnAction(e -> {
//
//            Button button = (Button) e.getSource();
////            button.getScaleX();
////
////            int btnID = Integer.parseInt(button.getId());
////            BindDataHolder.getBindDataHolderInstance().setColumnIndex(btnID);
////            System.out.println(button.getId());
//
//            // Open create new task stage
//            try {
//                StageHelper.getStageHelperInstance().createChildStage(stage, "Create Task", "view/create-task-view.fxml");
//                ColumnUIAdapter columnUIAdapter = projectUIAdapter.getColumns().getLast();
//                columnUIAdapter.setTasks(new TaskUIAdapter(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).getColumn().getLast(), projectUIAdapter.getColumns().get(BindDataHolder.getBindDataHolderInstance().getTabIndex())));
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

    }

    public void onRenameMenuItemClicked(ActionEvent event) throws IOException {
        BindDataHolder.getBindDataHolderInstance().setTabIndex(tabPane.getSelectionModel().getSelectedIndex());
        System.out.println(tabPane.getSelectionModel().getSelectedIndex());
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Rename Project", "view/rename-project-view.fxml");
    }

    public void onSetAsDefaultMenuItemClicked(ActionEvent event) {
        System.out.println("Set project as default");
    }

    public void onDeleteMenuItemClicked(ActionEvent event) {

    }
}
