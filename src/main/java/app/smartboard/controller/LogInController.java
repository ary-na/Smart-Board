package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.model.User;
import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.SceneHelper;
import app.smartboard.model.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
    public void onLogInButtonClick() throws SQLException, IOException, ClassNotFoundException {
        stage = (Stage) logInButton.getScene().getWindow();
        User user = Model.getModelInstance().getDatabaseHelper().getUser(username.getText(), psw.getText());
        Model.getModelInstance().setCurrentUser(user);
        System.out.println(user);
        if (user != null) {
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
