package app.smartboard.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ControllerHelper {

    private static ControllerHelper controllerHelperInstance;
    private final StringProperty name = new SimpleStringProperty();

    private ControllerHelper() {
    }

    public synchronized static ControllerHelper getControllerHelperInstance() {
        if (controllerHelperInstance == null)
            controllerHelperInstance = new ControllerHelper();
        return controllerHelperInstance;
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
