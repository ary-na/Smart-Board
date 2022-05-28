package app.smartboard.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task extends Nameable implements Serializable {

    private LocalDate dueDate;
    private boolean completed;
    private String description;

    public Task(String name) {
        super(name);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
