package app.smartboard.model;

public class NameableFactory {
    public Nameable createNameable(String modelType, String modelName) {

        if (modelType == null || modelType.isEmpty())
            return null;

        switch (modelType) {
            case "Project" -> {
                return new Project(modelName);
            }
            case "Column" -> {
                return new Column(modelName);
            }
            case "Task" -> {
                return new Task(modelName);
            }
        }
        return null;
    }
}
