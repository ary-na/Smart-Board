package app.smartboard.model;

import app.smartboard.SmartBoardApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHelper {

    private static StageHelper stageHelperInstance;
    private Stage stage;
    private Scene scene;

    private StageHelper() {
    }

    public synchronized static StageHelper getStageHelperInstance() {
        if (stageHelperInstance == null)
            stageHelperInstance = new StageHelper();
        return stageHelperInstance;
    }

    public void changeStage(String title, String fxml) throws IOException {
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SmartBoardApplication.class.getResource(fxml));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(title);
        if(fxml.equals("view/log-in-view.fxml")){
            stage.setResizable(false);
        }
        stage.show();
    }

    public void createChildStage(Stage owner, String title, String fxml) throws IOException {
        stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        FXMLLoader fxmlLoader = new FXMLLoader(SmartBoardApplication.class.getResource(fxml));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
