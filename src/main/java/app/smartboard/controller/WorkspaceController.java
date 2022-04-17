package app.smartboard.controller;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class WorkspaceController {

    @FXML
    public TabPane tabPane;
    @FXML
    private Button logOutButton;

    @FXML
    private Label quoteLabel;

    public void initialize(){
        int min = 1;
        int max = 10;
        Random random = new Random();
        int no = random.nextInt(max) + min;
        String quote = DatabaseHelper.getDatabaseHelperInstance().getQuote(no);
        quoteLabel.setText(quote);
    }

    public void onProfileButtonClick(ActionEvent event) throws IOException {
        StageHelper.getStageHelperInstance().createStage("Edit Profile", "view/edit-profile-view.fxml");
    }

    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
        StageHelper.getStageHelperInstance().changeStage("Smart Board", "view/log-in-view.fxml");

    }

    public void onNewProjectMenuItemClicked(ActionEvent actionEvent) throws IOException {
        StageHelper.getStageHelperInstance().createStage("Enter project name", "view/confirm-name-view.fxml");
        Model project = new Project("project 1");

        Tab tab = new Tab("project1");
        tabPane.getTabs().add(tab);
    }
}
