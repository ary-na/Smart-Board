package app.smartboard.model;

import java.util.Date;
import java.util.LinkedList;

public class Task extends Nameable {

    private String description;
    private Date dueDate;
    private final LinkedList<String> checkList;

    public Task(String name) {
        super(name);
        this.checkList = new LinkedList<String>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public LinkedList<String> getCheckList() {
        return checkList;
    }

    public void addCheckListItem(String checkListItem) {
        checkList.addFirst(checkListItem);
    }
}
