package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.Column;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ViewColumn extends VBox {

    public ViewColumn(Column column, Tab tab) {

        // Set column properties
        setSpacing(20);
        setPadding(new Insets(20));
        setMinHeight(Control.USE_COMPUTED_SIZE);
        setMinWidth(Control.USE_COMPUTED_SIZE);
        prefHeight(Control.USE_COMPUTED_SIZE);
        prefWidth(Control.USE_COMPUTED_SIZE);
        setMaxHeight(Control.USE_COMPUTED_SIZE);
        setMaxWidth(Control.USE_COMPUTED_SIZE);
        getStyleClass().add("vbox-column");

        // Get tab content
        ScrollPane scrollPane = (ScrollPane) tab.getContent();
        HBox hBox = (HBox) scrollPane.getContent();

        // Add column to tab
        hBox.getChildren().add(this);

        // Create column header
        HBox columnHeader = new HBox();
        columnHeader.setPrefWidth(300.00);
        columnHeader.setPrefHeight(25.00);
        columnHeader.setAlignment(Pos.CENTER);
        columnHeader.getStyleClass().add("hbox-column-header");

        // Column header label
        Label columnName = new Label(column.getName());

        // Column header region
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        // Column header button
        Button createTaskButton = new Button();
        ImageView plusIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/plus-icon.png"))));
        createTaskButton.setGraphic(plusIconImageView);
        createTaskButton.getStyleClass().add("button-create-task");

        // Add column header children
        columnHeader.getChildren().addAll(
                columnName,
                region,
                createTaskButton
        );

        // Add column header to column
        getChildren().add(columnHeader);
    }
}
