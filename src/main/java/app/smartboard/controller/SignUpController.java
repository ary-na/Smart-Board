package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.model.Profile;
import app.smartboard.model.database.DatabaseHelper;
import app.smartboard.model.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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


    public void onSignUpButtonClick() throws SQLException, IOException {
        Profile profile = new Profile();
        profile.setFirstName(firstName.getText());
        profile.setLastName(lastName.getText());
        Model.getModelInstance().getDatabaseHelper().createUser(username.getText(), password.getText(), profile);
        System.out.println("User created");
    }

    public void onCancelButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        SceneHelper.getSceneHelperInstance().changeScene(stage, "view/log-in-view.fxml");
    }
}
