package app.smartboard.model;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;

import java.util.LinkedList;

public class ProjectUIAdapter extends Tab {

    private LinkedList<ColumnUIAdapter> columns;
    public ProjectUIAdapter(Project project) {

        columns = new LinkedList<>();

        setText(project.getName());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMinHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMinWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.prefHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.prefWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxWidth(Control.USE_COMPUTED_SIZE);
        setContent(scrollPane);

        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(20));
        hBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        hBox.prefHeight(Control.USE_COMPUTED_SIZE);
        hBox.prefWidth(Control.USE_COMPUTED_SIZE);
        hBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        hBox.getStyleClass().add("hbox-project");
        scrollPane.setContent(hBox);
    }

    public LinkedList<ColumnUIAdapter> getColumns() {
        return columns;
    }

    public void setColumns(ColumnUIAdapter column) {
        this.columns.addLast(column);
    }
}
