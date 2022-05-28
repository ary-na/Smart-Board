package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.Column;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

// ColumnView UI
public class ColumnView extends VBox {

    private final Column column;
    private final ObservableList<TaskView> taskViews;
    private final VBox taskContainer;

    public ColumnView(Column column) {
        this.column = column;
        this.taskViews = FXCollections.observableArrayList();
        this.taskContainer = new VBox();
        Bindings.bindContent(this.taskContainer.getChildren(), this.taskViews);
        columnLayout();
    }

    public Column getColumn() {
        return column;
    }

    public ObservableList<TaskView> getTaskViews() {
        return taskViews;
    }

    public void addTaskView(TaskView taskView) {
        this.taskViews.add(taskView);
    }

    private void columnLayout() {

        // Set column properties
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setMinHeight(Control.USE_COMPUTED_SIZE);
        this.setMinWidth(Control.USE_COMPUTED_SIZE);
        this.prefHeight(Control.USE_COMPUTED_SIZE);
        this.prefWidth(Control.USE_COMPUTED_SIZE);
        this.setMaxHeight(Control.USE_COMPUTED_SIZE);
        this.setMaxWidth(Control.USE_COMPUTED_SIZE);
        this.getStyleClass().add("vbox-column");

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

        // Column header add task button
        ImageView plusIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/plus-icon.png"))));
        plusIconImageView.setFitHeight(12);
        plusIconImageView.setFitWidth(12);
        Button columnHeaderAddTaskButton = new Button();
        columnHeaderAddTaskButton.setGraphic(plusIconImageView);
        columnHeaderAddTaskButton.getStyleClass().add("button-create-task");

        // Column header menu button
        ImageView menuIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/column-header-menu-icon.png"))));
        menuIconImageView.setFitWidth(12);
        menuIconImageView.setFitHeight(12);
        MenuItem rename = new MenuItem("Rename");
        MenuItem delete = new MenuItem("Delete");
        MenuButton columnHeaderMenuButton = new MenuButton("", null, rename, delete);
        columnHeaderMenuButton.setGraphic(menuIconImageView);
        columnHeaderMenuButton.getStyleClass().add("menu-button-column");

        // Add column header children
        columnHeader.getChildren().addAll(
                columnName,
                region,
                columnHeaderAddTaskButton,
                columnHeaderMenuButton
        );

        // Set task container properties
        this.taskContainer.setSpacing(20);
        this.taskContainer.setMinHeight(Control.USE_COMPUTED_SIZE);
        this.taskContainer.setMinWidth(Control.USE_COMPUTED_SIZE);
        this.taskContainer.prefHeight(Control.USE_COMPUTED_SIZE);
        this.taskContainer.prefWidth(Control.USE_COMPUTED_SIZE);
        this.taskContainer.setMaxHeight(Control.USE_COMPUTED_SIZE);
        this.taskContainer.setMaxWidth(Control.USE_COMPUTED_SIZE);

        // Add tasks to task container
        this.taskContainer.getChildren().addAll(this.taskViews);

        // Add column header to column
        this.getChildren().addAll(
                columnHeader,
                taskContainer
        );
    }
}
