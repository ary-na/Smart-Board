package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.Column;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ColumnView extends VBox {

    private final Column column;
    private final Tab projectTab;

    public ColumnView(Column column, Tab projectTab) {
        this.column = column;
        this.projectTab = projectTab;
        columnLayout();
    }

    private void columnLayout() {
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
        ScrollPane scrollPane = (ScrollPane) this.projectTab.getContent();
        HBox hBox = (HBox) scrollPane.getContent();

        // Add column to tab
        hBox.getChildren().add(this);

        // Create column header
        HBox columnHeader = new HBox();
        columnHeader.setPrefWidth(300);
        columnHeader.setPrefHeight(15);
        columnHeader.setAlignment(Pos.CENTER);
        columnHeader.getStyleClass().add("hbox-column-header");

        // Column header label
        Label columnName = new Label(column.getName());

        // Column header region
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        // Column header Add Task button
        ImageView plusIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/plus-icon.png"))));
        plusIconImageView.setFitHeight(12);
        plusIconImageView.setFitWidth(12);
        Button columnHeaderAddTaskButton = new Button();
        columnHeaderAddTaskButton.setGraphic(plusIconImageView);
        columnHeaderAddTaskButton.getStyleClass().add("button-create-task");

        // Column header Menu button
        ImageView menuIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/menu-icon.png"))));
        menuIconImageView.setFitWidth(12);
        menuIconImageView.setFitHeight(12);
        MenuItem rename = new MenuItem("Rename");
        MenuItem delete = new MenuItem("Delete");
        MenuButton columnHeaderMenuButton = new MenuButton("", null, rename, delete);
        columnHeaderMenuButton.setGraphic(menuIconImageView);


        // Add column header children
        columnHeader.getChildren().addAll(
                columnName,
                region,
                columnHeaderAddTaskButton,
                columnHeaderMenuButton
        );

        // Add column header to column
        getChildren().add(columnHeader);
    }
}
