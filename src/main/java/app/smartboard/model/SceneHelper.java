package app.smartboard.model;

import app.smartboard.SmartBoardApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneHelper {

    private static SceneHelper sceneHelperInstance;
    private Stage stage;

    private SceneHelper() { }

    public synchronized static SceneHelper getSceneHelperInstance() {
        if (sceneHelperInstance == null)
            sceneHelperInstance = new SceneHelper();
        return sceneHelperInstance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(SmartBoardApplication.class.getResource(fxml)));
        stage.getScene().setRoot(pane);
    }
}
