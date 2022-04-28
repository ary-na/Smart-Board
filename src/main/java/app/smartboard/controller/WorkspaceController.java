package app.smartboard.controller;

import app.smartboard.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

public class WorkspaceController {

    @FXML
    public MenuBar workspaceMenuBar;
    Stage stage;
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
        profilePhotoImageView.setImage(new Image(new ByteArrayInputStream(Model.getModelInstance().getCurrentUser().getProfile().getProfilePhoto())));
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
        Nameable nameable = nameableFactory.createNameable("Project", ControllerHelper.getControllerHelperInstance().getName());
        Tab tab = new Tab(nameable.getName());
        tabPane.getTabs().add(tab);

        VBox vBox = new VBox(20);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Label label = new Label("Im new here");
        vBox.getChildren().addAll(label, scrollPane);


        tab.setContent(vBox);



    }
}
