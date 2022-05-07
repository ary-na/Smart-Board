package app.smartboard.model;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

public class TaskUIAdapter extends VBox {

    public TaskUIAdapter(Column column, VBox projectColumn) {

        VBox task = new VBox(20);
        task.setPadding(new Insets(20));
        task.setMinHeight(Control.USE_COMPUTED_SIZE);
        task.setMinWidth(Control.USE_COMPUTED_SIZE);
        task.prefHeight(Control.USE_COMPUTED_SIZE);
        task.prefWidth(Control.USE_COMPUTED_SIZE);
        task.setMaxHeight(Control.USE_COMPUTED_SIZE);
        task.setMaxWidth(Control.USE_COMPUTED_SIZE);
        task.getStyleClass().add("vbox-task");

        projectColumn.getChildren().add(task);

        Label label = new Label(column.getTask().getLast().getName());
        task.getChildren().add(label);
    }
}
