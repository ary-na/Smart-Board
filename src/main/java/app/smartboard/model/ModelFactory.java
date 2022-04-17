package app.smartboard.model;

public class ModelFactory {
    public Model createModel(String modelType, String modelName) {

        Model model = null;
        if (modelType == null || modelType.isEmpty())
            return null;

        switch (modelType) {
            case "project" -> model = new Project(modelName);
            case "column" -> model = new Column(modelName);
            case "task" -> model = new Task(modelName);
        }
        return model;
    }
}
