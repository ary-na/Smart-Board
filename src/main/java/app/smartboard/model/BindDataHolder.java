package app.smartboard.model;

import javafx.beans.property.*;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class BindDataHolder {

    private static BindDataHolder bindDataHolderInstance;
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty tabIndex = new SimpleIntegerProperty();
    private final IntegerProperty columnIndex = new SimpleIntegerProperty();

    private BindDataHolder() {
    }

    public synchronized static BindDataHolder getBindDataHolderInstance() {
        if (bindDataHolderInstance == null)
            bindDataHolderInstance = new BindDataHolder();
        return bindDataHolderInstance;
    }


    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getNameProperty() {
        return name;
    }


    public int getTabIndex() {
        return tabIndex.get();
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex.set(tabIndex);
    }

    public IntegerProperty tabIndexProperty() {
        return tabIndex;
    }


    public int getColumnIndex() {
        return this.columnIndex.get();
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex.set(columnIndex);
    }

    public IntegerProperty columnMapProperty() {
        return columnIndex;
    }
}
