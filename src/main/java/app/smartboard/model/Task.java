package app.smartboard.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Task extends Nameable implements Serializable {

    private LocalDate dueDate;
    private String description;
    private boolean completed;
    private final LinkedList<String> checkList;

    public Task(String name) {
        super(name);
        this.checkList = new LinkedList<>();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LinkedList<String> getCheckList() {
        return checkList;
    }

    public void addCheckListItem(String checkListItem) {
        checkList.addFirst(checkListItem);
    }
}
