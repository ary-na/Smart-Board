package app.smartboard.model;


import java.io.Serializable;
import java.util.LinkedList;

public class Project extends Nameable implements Serializable {

    private boolean isDefault;
    private final LinkedList<Column> column;

    public Project(String name) {
        super(name);
        this.isDefault = false;
        this.column = new LinkedList<>();
    }

    public boolean getDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public LinkedList<Column> getColumn() {
        return column;
    }

}
