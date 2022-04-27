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

    // Check this link and if worked add it to the readme file
    // https://stackoverflow.com/questions/22192484/how-to-close-and-iconify-a-javafx-stage
    public void createStage(Stage Owner, String title, String fxml) throws IOException {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(SmartBoardApplication.class.getResource(fxml));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
