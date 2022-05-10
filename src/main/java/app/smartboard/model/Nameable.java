package app.smartboard.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Nameable {
    private final StringProperty name = new SimpleStringProperty();

    public Nameable(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
}
