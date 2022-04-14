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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/log-in-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
        stage.setTitle("Smart Board");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}