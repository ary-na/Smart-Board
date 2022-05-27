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

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public LinkedList<Column> getColumn() {
        return column;
    }

    public void addColumn(Column column) {
        this.column.addLast(column);
    }
}
