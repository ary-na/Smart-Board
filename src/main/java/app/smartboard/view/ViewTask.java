package app.smartboard.view;

import app.smartboard.model.Column;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ViewTask extends VBox {

    public ViewTask(Column column, VBox projectColumn) {

        // Set VBox properties
        setSpacing(20);
        setPadding(new Insets(20));
        setMinHeight(Control.USE_COMPUTED_SIZE);
        setMinWidth(Control.USE_COMPUTED_SIZE);
        prefHeight(Control.USE_COMPUTED_SIZE);
        prefWidth(Control.USE_COMPUTED_SIZE);
        setMaxHeight(Control.USE_COMPUTED_SIZE);
        setMaxWidth(Control.USE_COMPUTED_SIZE);
        getStyleClass().add("vbox-task");

        // Add task to column
        projectColumn.getChildren().add(this);

        // Create task
        Label label = new Label(column.getTask().getLast().getName());

        // Add task children
        getChildren().add(label);
    }
}
