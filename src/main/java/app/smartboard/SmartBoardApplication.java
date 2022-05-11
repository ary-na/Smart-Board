package app.smartboard;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartBoardApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory viewFactory = new ViewFactory(new Model());
        viewFactory.displayLoginView();
    }
}