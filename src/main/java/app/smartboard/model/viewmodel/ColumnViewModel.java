package app.smartboard.model.viewmodel;

import app.smartboard.model.Column;
import app.smartboard.view.ColumnView;
import app.smartboard.view.ProjectView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class ColumnViewModel {

    private ObservableList<VBox> columnVBoxes = FXCollections.observableArrayList();
    private HashMap<ColumnView, Column> columnMap = new HashMap<>();
    private HashMap<ProjectView, ObservableList<VBox>> projectColumnView = new HashMap<>();
    private VBox column;

    public ObservableList<VBox> getColumnVBoxes() {
        return columnVBoxes;
    }

    public void setColumnVBoxes(ObservableList<VBox> columnVBoxes) {
        this.columnVBoxes = columnVBoxes;
    }

    public HashMap<ColumnView, Column> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(HashMap<ColumnView, Column> columnMap) {
        this.columnMap = columnMap;
    }

    public HashMap<ProjectView, ObservableList<VBox>> getProjectColumnView() {
        return projectColumnView;
    }

    public void setProjectColumnView(HashMap<ProjectView, ObservableList<VBox>> projectColumnView) {
        this.projectColumnView = projectColumnView;
    }

    public VBox getColumn() {
        return column;
    }

    public void setColumn(VBox column) {
        this.column = column;
    }
}
