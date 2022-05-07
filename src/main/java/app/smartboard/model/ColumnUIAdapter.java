package app.smartboard.model;

import app.smartboard.SmartBoardApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.LinkedList;

public class ColumnUIAdapter extends VBox {

    private VBox columnUI;
    private Button createTaskButton;

    private LinkedList<TaskUIAdapter> tasks;

    public ColumnUIAdapter(Project project, Tab projectTab) {

        tasks = new LinkedList<>();

        columnUI = new VBox(20);
        columnUI.setPadding(new Insets(20));
        columnUI.setMinHeight(Control.USE_COMPUTED_SIZE);
        columnUI.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnUI.prefHeight(Control.USE_COMPUTED_SIZE);
        columnUI.prefWidth(Control.USE_COMPUTED_SIZE);
        columnUI.setMaxHeight(Control.USE_COMPUTED_SIZE);
        columnUI.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnUI.getStyleClass().add("vbox-column");

        ScrollPane scrollPane = (ScrollPane) projectTab.getContent();
        HBox hBox = (HBox) scrollPane.getContent();

        hBox.getChildren().add(columnUI);

        ImageView plusIconImageView = new ImageView(new Image(String.valueOf(SmartBoardApplication.class.getResource("/assets/plus-icon.png"))));

        createTaskButton = new Button();
        createTaskButton.setGraphic(plusIconImageView);

        Label columnName = new Label(project.getColumn().getLast().getName());

        HBox columnHeader = new HBox();
        columnHeader.setPrefWidth(300.00);
        columnHeader.setPrefHeight(25.00);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        columnHeader.setAlignment(Pos.CENTER);
        columnHeader.getStyleClass().add("hbox-column-header");

        columnHeader.getChildren().addAll(
                columnName,
                region,
                createTaskButton
        );
        columnUI.getChildren().add(columnHeader);
    }

    public VBox getColumn() {
        return columnUI;
    }

    public void setColumn(VBox column) {
        this.columnUI = column;
    }

    public Button getCreateTaskButton() {
        return createTaskButton;
    }

    public void setCreateTaskButton(Button button) {
        this.createTaskButton = button;
    }

    public LinkedList<TaskUIAdapter> getTasks() {
        return tasks;
    }

    public void setTasks(TaskUIAdapter task) {
        this.tasks.addLast(task);
    }
}
