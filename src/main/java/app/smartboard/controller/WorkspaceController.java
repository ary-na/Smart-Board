package app.smartboard.controller;

import app.smartboard.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

public class WorkspaceController {

    Stage stage;

    @FXML
    public MenuBar workspaceMenuBar;
    @FXML
    public ImageView profilePhotoImageView;
    @FXML
    public Label firstNameLabel;
    @FXML
    public TabPane tabPane;
    @FXML
    private Button logOutButton;
    @FXML
    private Label quoteLabel;

    public void initialize() {
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
        stage = (Stage) workspaceMenuBar.getScene().getWindow();
        StageHelper.getStageHelperInstance().createChildStage(stage, "Create Project", "view/confirm-name-view.fxml");
        NameableFactory nameableFactory = new NameableFactory();
        Nameable nameable = nameableFactory.createNameable("Project", BindDataHolder.getBindDataHolderInstance().getName());
        Tab tab = new Tab(nameable.getName());
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        ScrollPane scrollPane = UIAdapter.createProjectScrollPane();

        tab.setContent(scrollPane);

        /*
        the links I have used to code this section
        https://www.geeksforgeeks.org/javafx-background-class/
        http://www.java2s.com/Tutorials/Java/JavaFX_How_to/HBox/Add_two_controls_to_HBox.htm
        https://stackoverflow.com/questions/20454021/how-to-set-padding-between-columns-of-a-javafx-gridpane
        https://stackoverflow.com/questions/28573700/javafx-use-computed-size-for-stage-without-using-fxml
        https://stackoverflow.com/questions/51574433/java-fx-set-margin
        https://stackoverflow.com/questions/6902377/javafx-tabpane-how-to-set-the-selected-tab
         */


        HBox hBox = UIAdapter.createProjectHBox(scrollPane, UIAdapter.backgroundHBox);


        VBox vBox = UIAdapter.createColumn(UIAdapter.backgroundVBox);
        VBox vBox1 = UIAdapter.createColumn(UIAdapter.backgroundVBox);
        VBox vBox2 = UIAdapter.createColumn(UIAdapter.backgroundVBox);
        VBox vBox3 = UIAdapter.createColumn(UIAdapter.backgroundVBox);

        hBox.getChildren().addAll(vBox, vBox1, vBox2, vBox3);


        HBox task = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task1 = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task2 = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task3 = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task4 = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task5 = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task6 = UIAdapter.createTask(UIAdapter.backgroundTask);
        HBox task7 = UIAdapter.createTask(UIAdapter.backgroundTask);

        Label labeltest = new Label("Tesdlkgsd");
        Label labeltest1 = new Label("Tesdlkgsd");
        Label labeltest2 = new Label("Tesdlkgsd");
        Label labeltest3 = new Label("Tesdlkgsd");
        Label labeltest4 = new Label("Tesdlkgsd");
        Label labeltest5 = new Label("Tesdlkgsd");

        task.getChildren().add(labeltest);
        task1.getChildren().add(labeltest1);
        task2.getChildren().add(labeltest2);
        task3.getChildren().add(labeltest3);
        task4.getChildren().add(labeltest4);
        task4.getChildren().add(labeltest5);

        vBox1.getChildren().addAll(task, task1, task2, task6, task7);
        vBox.getChildren().addAll(task4);
        vBox2.getChildren().addAll(task3);
        vBox3.getChildren().add(task5);
    }

    public void onAddColumnMenuItemClicked(ActionEvent event) {
        System.out.println("Add column");
    }

    public void onRenameMenuItemClicked(ActionEvent event) {
        System.out.println("Rename project");
    }

    public void onSetAsDefaultMenuItemClicked(ActionEvent event) {
        System.out.println("Set project as default");
    }

    public void onDeleteMenuItemClicked(ActionEvent event) {
        System.out.println("Delete project");
    }
}
