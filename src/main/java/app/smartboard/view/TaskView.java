package app.smartboard.view;

import app.smartboard.SmartBoardApplication;
import app.smartboard.model.Task;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class TaskView extends VBox {

    private final Task task;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TaskView(Task task) {
        this.task = task;
        taskLayout();
    }

    private void taskLayout() {

        // Task left container
        VBox left = new VBox(5);
        left.setPrefHeight(45);
        left.setAlignment(Pos.CENTER_LEFT);
        left.getStyleClass().add("vbox-task-left-container");

        // Task title, due date and checklist
        Label taskTitle = new Label(this.task.getName());
        taskTitle.getStyleClass().add("label-task-title");
        Label taskDueDate = new Label("");
        if(task.getDueDate() != null)
            taskDueDate.setText(dateTimeFormatter.format(this.task.getDueDate()));

        left.getChildren().addAll(
                taskTitle,
                taskDueDate
        );

        // Task right container
        VBox right = new VBox(5);
        right.setPrefHeight(45);
        right.setAlignment(Pos.CENTER);
        right.getStyleClass().add("vbox-task-right-container");

        // Task Button
        ImageView menuIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/column-header-menu-icon.png"))));
        menuIconImageView.setFitWidth(12);
        menuIconImageView.setFitHeight(12);
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        MenuButton taskMenuButton = new MenuButton("", null, edit, delete);
        taskMenuButton.setGraphic(menuIconImageView);
        taskMenuButton.getStyleClass().add("menu-button-task");

        right.getChildren().addAll(
                taskMenuButton
        );

        // Task HBox container
        HBox taskContainer = new HBox();
        taskContainer.setMinHeight(Control.USE_COMPUTED_SIZE);
        taskContainer.setMinWidth(Control.USE_COMPUTED_SIZE);
        taskContainer.prefHeight(Control.USE_COMPUTED_SIZE);
        taskContainer.prefWidth(Control.USE_COMPUTED_SIZE);
        taskContainer.setMaxHeight(Control.USE_COMPUTED_SIZE);
        taskContainer.setMaxWidth(Control.USE_COMPUTED_SIZE);
        taskContainer.getStyleClass().add("hbox-task-container");

        // task container region
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        taskContainer.getChildren().addAll(
                left,
                region,
                right
        );

        // Set task VBox properties
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setMinHeight(Control.USE_COMPUTED_SIZE);
        this.setMinWidth(Control.USE_COMPUTED_SIZE);
        this.prefHeight(Control.USE_COMPUTED_SIZE);
        this.prefWidth(Control.USE_COMPUTED_SIZE);
        this.setMaxHeight(Control.USE_COMPUTED_SIZE);
        this.setMaxWidth(Control.USE_COMPUTED_SIZE);
        this.getStyleClass().add("vbox-task");

        // Add task VBox children
        this.getChildren().add(taskContainer);

    }
}
