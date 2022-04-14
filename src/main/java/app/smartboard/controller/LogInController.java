package app.smartboard.controller;

import app.smartboard.model.DatabaseHelper;
import app.smartboard.model.SceneHelper;
import app.smartboard.model.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    public Hyperlink signUpHyperlink;

    @FXML
    private Button logInButton;

    @FXML
    private TextField psw;

    @FXML
    private TextField username;

    private Stage stage;


    @FXML
    public void onLogInButtonClick() throws IOException {
        stage = (Stage) logInButton.getScene().getWindow();
        boolean loggedIn = DatabaseHelper.getDatabaseHelperInstance().validate(username.getText(), psw.getText());
        System.out.println(loggedIn);
        if (loggedIn) {
            stage.close();
            StageHelper.getStageHelperInstance().changeStage("Smart Board", "view/workspace-view.fxml");
        }
    }

    @FXML
    public void onSignUpHyperLinkClick(ActionEvent event) throws IOException {
        stage = (Stage) signUpHyperlink.getScene().getWindow();
        SceneHelper.getSceneHelperInstance().changeScene(stage, "view/sign-up-view.fxml");
    }
}
