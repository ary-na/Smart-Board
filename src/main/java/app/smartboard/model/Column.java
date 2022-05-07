package app.smartboard.model;

import java.util.LinkedList;

public class Column extends Nameable {

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

    public void deleteTask(Task task) {
        this.task.remove(task);
    }
}
