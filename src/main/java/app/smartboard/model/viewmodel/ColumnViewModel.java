package app.smartboard.model.viewmodel;

import app.smartboard.model.Column;
import app.smartboard.view.ViewColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class ColumnViewModel {

    private ObservableList<VBox> columnVBoxes = FXCollections.observableArrayList();
    private HashMap<ViewColumn, Column> columnMap = new HashMap<>();
    private VBox column;

    public ObservableList<VBox> getColumnVBoxes() {
        return columnVBoxes;
    }

    public void setColumnVBoxes(ObservableList<VBox> columnVBoxes) {
        this.columnVBoxes = columnVBoxes;
    }

    public HashMap<ViewColumn, Column> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(HashMap<ViewColumn, Column> columnMap) {
        this.columnMap = columnMap;
    }

    public VBox getColumn() {
        return column;
    }

    public void setColumn(VBox column) {
        this.column = column;
    }
}
