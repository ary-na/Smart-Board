package app.smartboard.model;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Project extends Model implements DefaultProject {

    private boolean isDefault;
    private final LinkedList<Column> column;

    public Project(String name) {
        super(name);
        this.isDefault = false;
        this.column = new LinkedList<Column>();
    }

    public LinkedList<Column> getColumn() {
        return column;
    }

    public void addColumn(Column column) {
        this.column.addLast(column);
    }

    public void deleteColumn(Column column) {
        this.column.remove(column);
    }

    @Override
    public void setDefaultProject(boolean set) {
        this.isDefault = set;
    }

    @Override
    public boolean getDefaultProject() {
        return this.isDefault;
    }
}
