package app.smartboard.controller;

import app.smartboard.model.DatabaseHelper;
import app.smartboard.model.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField psw;

    @FXML
    private TextField userName;


    @FXML
    public void onLoginButtonClick() throws IOException {
        boolean loggedIn = DatabaseHelper.getDatabaseHelperInstance().validate(userName.getText(), psw.getText());
        System.out.println(loggedIn);
        if(loggedIn)
            SceneHelper.getSceneHelperInstance().changeScene("view/workspace-view.fxml");

   }

    @FXML
    public void onCancelButtonClick() {
        System.exit(0);
    }

    @FXML
    public void onCreateNewUserAccountClick(ActionEvent event) throws IOException {
        SceneHelper.getSceneHelperInstance().changeScene("view/create-user-view.fxml");
    }
}
