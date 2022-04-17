package app.smartboard.controller;

import app.smartboard.model.DatabaseHelper;
import app.smartboard.model.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Button cancelButton;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField password;


    private Stage stage;


    public void onSignUpButtonClick() {
        DatabaseHelper.getDatabaseHelperInstance().createUser(username.getText(), firstName.getText(), lastName.getText(), password.getText());
        System.out.println("User created");
    }

    public void onCancelButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) cancelButton.getScene().getWindow();
        SceneHelper.getSceneHelperInstance().changeScene(stage, "view/log-in-view.fxml");
    }
}
