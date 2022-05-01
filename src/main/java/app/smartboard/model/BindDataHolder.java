package app.smartboard.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BindDataHolder {

    private static BindDataHolder bindDataHolderInstance;
    private final StringProperty name = new SimpleStringProperty();

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
}
