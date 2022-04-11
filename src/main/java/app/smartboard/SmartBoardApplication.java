package app.smartboard;

import app.smartboard.model.SceneHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartBoardApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Set the primary stage and get scene, switch root node
        SceneHelper.getSceneHelperInstance().setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Smart Board");
        stage.setScene(scene);
        stage.show();
    }
}