package app.smartboard.controller;

import app.smartboard.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkspaceController {

    private Stage stage;
    private final LinkedList<Tab> projectTabs = new LinkedList<>();
    private final LinkedList<VBox> projectColumns = new LinkedList<>();

    private final ObservableList<Node> projectUI = FXCollections.observableArrayList();

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

    public void initialize() {
        if (projectTabs.size() > 0)
            projectTabs.get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).setText(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).getName());
        if (Model.getModelInstance().getCurrentUser().getProfile().getProfilePhoto() != null) {
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(Model.getModelInstance().getCurrentUser().getProfile().getProfilePhoto())));
        }
        firstNameLabel.setText(Model.getModelInstance().getCurrentUser().getProfile().getFirstName());
        Random random = new Random();
        quoteLabel.setText(Model.getModelInstance().getDatabaseHelper().getQuote(random.nextInt(10) + 1));
    }

    public void onProfileButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Edit Profile", "view/edit-profile-view.fxml");
        initialize();
    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        StageHelper.getStageHelperInstance().changeStage("Smart Board", "view/log-in-view.fxml");
    }

    public void onNewProjectMenuItemClicked(ActionEvent event) throws IOException {
        // Create stage -> create new project
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Create Project", "view/create-project-view.fxml");

        // Create project UI
        ProjectUIAdapter projectUIAdapter = new ProjectUIAdapter(Model.getModelInstance().getProjects().getLast());
        tabPane.getTabs().add(projectUIAdapter);
        tabPane.getSelectionModel().select(projectUIAdapter);
        projectTabs.addLast(projectUIAdapter);
        for (Project project : Model.getModelInstance().getProjects())
            System.out.println(project.getName());

        /*
        scrollpane
        https://stackoverflow.com/questions/50184756/remove-border-around-javafx-scrollpane-scroll-bar-when-not-focused
        https://stackoverflow.com/questions/42808539/in-javafx-how-to-change-scrollbar-arrow-color
        https://stackoverflow.com/questions/41804373/javafx-scrollpane-styling
        https://stackoverflow.com/questions/16977100/how-do-i-add-margin-to-a-javafx-element-using-css
        https://stackoverflow.com/questions/20118574/set-background-color-in-scrollpane
        https://stackoverflow.com/questions/39962796/creating-map-composed-of-2-lists-using-stream-collect-in-java



        https://stackoverflow.com/questions/21865044/javafx-sethgrow-doesnt-work
        https://stackoverflow.com/questions/34211310/remove-the-glowing-border-from-focused-tab-with-css
        https://stackoverflow.com/questions/17091605/how-to-change-the-tab-pane-style
        https://stackoverflow.com/questions/41642403/how-to-add-css-to-a-javafx-element
         */
    }

    public void onAddColumnMenuItemClicked(ActionEvent event) throws IOException {
        // Get tab index
        BindDataHolder.getBindDataHolderInstance().setTabIndex(tabPane.getSelectionModel().getSelectedIndex());

        // Open create new column stage
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Create Column", "view/create-column-view.fxml");

        // Create column UI
        ProjectUIAdapter projectUIAdapter = (ProjectUIAdapter) projectTabs.get(BindDataHolder.getBindDataHolderInstance().getTabIndex());
        projectUIAdapter.setColumns(new ColumnUIAdapter(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()), projectTabs.get(BindDataHolder.getBindDataHolderInstance().getTabIndex())));

        projectUIAdapter.getColumns().getLast().getCreateTaskButton().setOnAction(e -> {

            Button button = (Button) e.getSource();
//            button.getScaleX();
//
//            int btnID = Integer.parseInt(button.getId());
//            BindDataHolder.getBindDataHolderInstance().setColumnIndex(btnID);
//            System.out.println(button.getId());

            // Open create new task stage
            try {
                StageHelper.getStageHelperInstance().createChildStage(stage, "Create Task", "view/create-task-view.fxml");
                ColumnUIAdapter columnUIAdapter = projectUIAdapter.getColumns().getLast();
                columnUIAdapter.setTasks(new TaskUIAdapter(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).getColumn().getLast(), projectUIAdapter.getColumns().get(BindDataHolder.getBindDataHolderInstance().getTabIndex())));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void onRenameMenuItemClicked(ActionEvent event) throws IOException {
        int tabIndex = tabPane.getSelectionModel().getSelectedIndex();
        BindDataHolder.getBindDataHolderInstance().setTabIndex(tabIndex);
        System.out.println("tab index = " + tabIndex);
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Rename Project", "view/rename-project-view.fxml");
        initialize();
    }

    public void onSetAsDefaultMenuItemClicked(ActionEvent event) {
        System.out.println("Set project as default");
    }

    public void onDeleteMenuItemClicked(ActionEvent event) {
        System.out.println("Delete project");
    }
}
