package app.smartboard.model.viewmodel;

import app.smartboard.model.Task;
import app.smartboard.view.TaskView;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class TaskViewModel {

    private HashMap<TaskView, Task> taskMap = new HashMap<>();
    private VBox task;

    public HashMap<TaskView, Task> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(HashMap<TaskView, Task> taskMap) {
        this.taskMap = taskMap;
    }

    public VBox getTask() {
        return task;
    }

    public void setTask(VBox task) {
        this.task = task;
    }
}
