package app.smartboard.view;

import app.smartboard.model.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ViewTask extends VBox {

    private final Task task;
    private final VBox projectColumn;

    public ViewTask(Task task, VBox projectColumn) {
        this.task = task;
        this.projectColumn = projectColumn;
        taskLayout();
    }

    private void taskLayout() {

        // Set VBox properties
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setMinHeight(Control.USE_COMPUTED_SIZE);
        this.setMinWidth(Control.USE_COMPUTED_SIZE);
        this.prefHeight(Control.USE_COMPUTED_SIZE);
        this.prefWidth(Control.USE_COMPUTED_SIZE);
        this.setMaxHeight(Control.USE_COMPUTED_SIZE);
        this.setMaxWidth(Control.USE_COMPUTED_SIZE);
        this.getStyleClass().add("vbox-task");

        // Add Vbox child to the task layout

        // Add task to column
        this.projectColumn.getChildren().add(this);

        // Create task
        Label label = new Label(this.task.getName());

        // Add task children
        getChildren().add(label);

    }
}
