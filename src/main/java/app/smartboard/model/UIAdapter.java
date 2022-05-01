package app.smartboard.model;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UIAdapter {

    public static BackgroundFill background_fill = new BackgroundFill(Color.PINK,
            CornerRadii.EMPTY, Insets.EMPTY);

    public static BackgroundFill background_fill1 = new BackgroundFill(Color.GREEN,
            CornerRadii.EMPTY, Insets.EMPTY);

    public static BackgroundFill background_fill2 = new BackgroundFill(Color.YELLOW,
            CornerRadii.EMPTY, Insets.EMPTY);

    public static Background backgroundHBox = new Background(background_fill);

    public static Background backgroundVBox = new Background(background_fill1);
    public static Background backgroundTask = new Background(background_fill2);

    public static ScrollPane createProjectScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMinHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMinWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.prefHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.prefWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxWidth(Control.USE_COMPUTED_SIZE);
        return scrollPane;
    }

    public static HBox createProjectHBox(ScrollPane scrollPane, Background backgroundHBox) {
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(20));
        hBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        hBox.prefHeight(Control.USE_COMPUTED_SIZE);
        hBox.prefWidth(Control.USE_COMPUTED_SIZE);
        hBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.setContent(hBox);
        hBox.setBackground(backgroundHBox);
        return hBox;
    }

    public static VBox createColumn(Background backgroundVBox) {
        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));
        vBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBox.prefHeight(Control.USE_COMPUTED_SIZE);
        vBox.prefWidth(Control.USE_COMPUTED_SIZE);
        vBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
        vBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBox.setBackground(backgroundVBox);
        return vBox;
    }

    public static HBox createTask(Background backgroundTask) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(20.00);
        hBox.setPrefWidth(300.00);
        hBox.setPrefHeight(100.00);
        hBox.setBackground(backgroundTask);
        return hBox;
    }
}
