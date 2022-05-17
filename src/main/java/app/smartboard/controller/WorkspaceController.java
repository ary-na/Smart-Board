package app.smartboard.controller;

import app.smartboard.model.*;
import app.smartboard.view.ViewFactory;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

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

        //this.model.getProjects().forEach(project -> this.model.getProjectUI().add(new ViewProjectFactory(project)));

        if (this.model.getProjects().isEmpty() && this.model.getProjects() != null)
            this.model.getWorkspaceViewModel().setEmptyWorkspace(true);

        Bindings.bindBidirectional(addColumnMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(renameProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(deleteProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());
        Bindings.bindBidirectional(setDefaultProjectMenuItem.disableProperty(), this.model.getWorkspaceViewModel().emptyWorkspaceProperty());

        // Bind user profile picture and first name to view model
        Bindings.bindBidirectional(profilePhotoImageView.imageProperty(), this.model.getWorkspaceViewModel().userImageProperty());

        Bindings.bindBidirectional(firstNameLabel.textProperty(), this.model.getWorkspaceViewModel().userFirstNameProperty());
        Bindings.bindContent(tabPane.getTabs(), this.model.getProjectViewModel().getProjectTabs());

        // Load user data
        if (this.model.getCurrentUser().getProfile().getProfilePhoto() != null) {
            firstNameLabel.setText(this.model.getCurrentUser().getProfile().getFirstName());
            profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(this.model.getCurrentUser().getProfile().getProfilePhoto())));
        }

        // Select a random quote
        Random random = new Random();
        quoteLabel.setText(this.model.getDatabaseHelper().getQuote(random.nextInt(10) + 1));


        this.model.getColumnViewModel().getColumnVBoxes().forEach(column -> column.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            VBox vBox = (VBox) e.getSource();
            vBox.getId();
            HBox hBox = (HBox) vBox.getChildren().get(0);


            this.model.getColumnViewModel().setColumn(column);
            int projectIndex = this.model.getProjectViewModel().getTabPane().getSelectionModel().getSelectedIndex();
            int columnIndex = this.model.getProjects().get(projectIndex).getColumn().indexOf(this.model.getColumnViewModel().getColumnMap().get(column));

            System.out.println("This column is selected" + this.model.getProjects().get(projectIndex).getColumn().get(columnIndex).getName());

            ScrollPane scrollPane = (ScrollPane) this.model.getProjectViewModel().getTabPane().getSelectionModel().getSelectedItem().getContent();
            HBox hBox3 = (HBox) scrollPane.getContent();


            for(Node node5: hBox3.getChildren()){
                System.out.println(node5.getId());

            }

                for(Node node: hBox.getChildren()){
                    if(node instanceof Button button){
                        button.setOnAction(event2 -> {
                            System.out.println("onAddColumnButtonClicked");
                            // Display Edit Profile view
                            this.stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                            try {
                                viewFactory.displayCreateTaskView(stage);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }
                }
        }));
//        if(tabPane != null) {
//            Tab tab = tabPane.getSelectionModel().getSelectedItem();
//            ScrollPane scrollPane = (ScrollPane) tab.getContent();
//            HBox hBox = (HBox) scrollPane.getContent();
//            hBox.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
//                Node node = (Node) e.getSource();
//
//                System.out.println("Arian" + node.getId());
//            });
//        }

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

        // Get tab index
//        BindDataHolder.getBindDataHolderInstance().setTabIndex(tabPane.getSelectionModel().getSelectedIndex());
//
//        // Open create new column stage
//        stage = (Stage) workspaceMenuBar.getScene().getWindow();
//        StageHelper.getStageHelperInstance().createChildStage(stage, "Create Column", "view/create-colum-view.fxml");
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
//                StageHelper.getStageHelperInstance().createChildStage(stage, "Create Task", "view/create-tak-view.fxml");
//                ColumnUIAdapter columnUIAdapter = projectUIAdapter.getColumns().getLast();
//                columnUIAdapter.setTasks(new TaskUIAdapter(Model.getModelInstance().getProjects().get(BindDataHolder.getBindDataHolderInstance().getTabIndex()).getColumn().getLast(), projectUIAdapter.getColumns().get(BindDataHolder.getBindDataHolderInstance().getTabIndex())));
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

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



}
