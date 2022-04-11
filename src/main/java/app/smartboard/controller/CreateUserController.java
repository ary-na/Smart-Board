package app.smartboard.controller;

import app.smartboard.model.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateUserController {

    @FXML
    private Button closeButton;
    @FXML
    private TextField userName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField psw;
    @FXML
    private TextField profilePhoto;


    public void onCreateUserButtonClick() {
//        DatabaseHelper.createUser(userName.getText(), firstName.getText(), lastName.getText(), psw.getText(), profilePhoto.getText());
//        System.out.println("User created");
    }

    public void onCloseButtonClick(ActionEvent event) throws IOException {
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login-view.fxml"));
//        stage.setScene(new Scene(fxmlLoader.load()));
        SceneHelper.getSceneHelperInstance().changeScene("view/login-view.fxml");
    }
}
