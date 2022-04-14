package app.smartboard.model;

import app.smartboard.SmartBoardApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneHelper {

    private static SceneHelper sceneHelperInstance;

    private SceneHelper() {
    }

    public synchronized static SceneHelper getSceneHelperInstance() {
        if (sceneHelperInstance == null)
            sceneHelperInstance = new SceneHelper();
        return sceneHelperInstance;
    }

    public void changeScene(Stage stage, String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(SmartBoardApplication.class.getResource(fxml)));
        stage.getScene().setRoot(pane);
    }
}
