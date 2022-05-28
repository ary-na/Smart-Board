package app.smartboard.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Column extends Nameable implements Serializable {

    private final LinkedList<Task> task;

    public Column(String name) {
        super(name);
        this.task = new LinkedList<>();
    }

    public LinkedList<Task> getTask() {
        return task;
    }

    public void addTask(Task task) {
        this.task.addLast(task);
    }

}
