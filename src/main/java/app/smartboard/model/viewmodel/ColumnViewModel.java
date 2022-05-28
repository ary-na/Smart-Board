package app.smartboard.model.viewmodel;

import app.smartboard.model.Column;
import app.smartboard.view.ColumnView;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class ColumnViewModel {

    private HashMap<ColumnView, Column> columnMap = new HashMap<>();
    private VBox column;

    public HashMap<ColumnView, Column> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(HashMap<ColumnView, Column> columnMap) {
        this.columnMap = columnMap;
    }

    public VBox getColumn() {
        return column;
    }

    public void setColumn(VBox column) {
        this.column = column;
    }
}
