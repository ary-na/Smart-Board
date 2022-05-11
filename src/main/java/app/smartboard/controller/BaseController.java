package app.smartboard.controller;

import app.smartboard.model.Model;
import app.smartboard.view.ViewFactory;
import javafx.stage.Stage;

/*
 * Code sourced and adapted from:
 * https://www.udemy.com/course/advanced-programming-with-javafx-build-an-email-client/
 * https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/968af113fc73cc16454d26ab95db0fa5962b7a34
 */

public abstract class BaseController {

    protected Model model;
    protected ViewFactory viewFactory;
    private String fxml;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    public BaseController(Model model, ViewFactory viewFactory, String fxml) {
        this.model = model;
        this.viewFactory = viewFactory;
        this.fxml = fxml;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setViewFactory(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    public String getFxml() {
        return fxml;
    }

    public void setFxml(String fxml) {
        this.fxml = fxml;
    }
}
