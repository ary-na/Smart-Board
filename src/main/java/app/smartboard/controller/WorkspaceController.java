package app.smartboard.controller;

import app.smartboard.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

public class WorkspaceController {

    @FXML
    public ImageView profilePhotoWorkspace;
    @FXML
    public Label firstNameLabelWorkspace;
    @FXML
    public TabPane tabPane;
    @FXML
    private Button logOutButton;

    @FXML
    private Label quoteLabel;

    public void initialize() {
        profilePhotoWorkspace.setImage(new Image(new ByteArrayInputStream(Model.getModelInstance().getCurrentUser().getProfile().getProfilePhoto())));
        firstNameLabelWorkspace.setText(Model.getModelInstance().getCurrentUser().getProfile().getFirstName());
        Random random = new Random();
        quoteLabel.setText(Model.getModelInstance().getDatabaseHelper().getQuote(random.nextInt(10) + 1));
    }

    public void onProfileButtonClick(ActionEvent event) throws IOException {
        StageHelper.getStageHelperInstance().createStage("Edit Profile", "view/edit-profile-view.fxml");
    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        StageHelper.getStageHelperInstance().changeStage("Smart Board", "view/log-in-view.fxml");
    }

    public void onNewProjectMenuItemClicked(ActionEvent event) throws IOException {

        StageHelper.getStageHelperInstance().createStage("Create Project", "view/confirm-name-view.fxml");
        NameableFactory nameableFactory = new NameableFactory();
        Nameable nameable = nameableFactory.createNameable("Project", ControllerHelper.getControllerHelperInstance().getName());
        Tab tab = new Tab(nameable.getName());
        tabPane.getTabs().add(tab);
        GridPane gridPane = new GridPane();
    }
}
