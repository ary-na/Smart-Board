package app.smartboard.model.viewmodel;

import app.smartboard.model.Task;
import app.smartboard.view.TaskView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class TaskViewModel {
    private ObservableList<VBox> taskVBoxes = FXCollections.observableArrayList();
    private HashMap<TaskView, Task> taskMap = new HashMap<>();
    private VBox task;

    public ObservableList<VBox> getTaskVBoxes() {
        return taskVBoxes;
    }

    public void setTaskVBoxes(ObservableList<VBox> taskVBoxes) {
        this.taskVBoxes = taskVBoxes;
    }

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
