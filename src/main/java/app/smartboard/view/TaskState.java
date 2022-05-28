package app.smartboard.view;

import javafx.scene.control.Label;

/*
 * Code sourced and adapted from:
 * https://www.udemy.com/course/advanced-programming-with-javafx-build-an-email-client/
 * https://github.com/barosanuemailtest/JavaFxEmailClientCourse/commit/695286c30a82dd7acfa718303d31b3a65301812b
 */

// Task state enum
public enum TaskState {

    NO_DATE,
    DEFAULT,
    COMPLETED,
    DUE,
    OVERDUE;

    // Get CSS class on task state
    public static String getCSSClass(TaskState taskState) {
        switch (taskState) {
            case NO_DATE -> {
                return "label-task-state-no-date";
            }
            case DEFAULT -> {
                return "label-task-state-default";
            }
            case COMPLETED -> {
                return "label-task-state-completed";
            }
            case DUE -> {
                return "label-task-state-due";
            }
            case OVERDUE -> {
                return "label-task-state-overdue";
            }
            default -> {
                return null;
            }
        }
    }

    // Remove CSS class from due date label
    public static void removeCSSClass(Label dueDateLabel) {
        dueDateLabel.getStyleClass().remove("label-task-state-no-date");
        dueDateLabel.getStyleClass().remove("label-task-state-default");
        dueDateLabel.getStyleClass().remove("label-task-state-completed");
        dueDateLabel.getStyleClass().remove("label-task-state-due");
        dueDateLabel.getStyleClass().remove("label-task-state-overdue");
    }
}
