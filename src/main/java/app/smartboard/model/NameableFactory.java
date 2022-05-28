package app.smartboard.model;

public class NameableFactory {

    // Create nameable object base on nameable type
    public Nameable createNameable(String nameableType, String nameableName) {

        // Create and return nameable on condition
        if (nameableType == null || nameableName.isEmpty())
            return null;

        switch (nameableType) {
            case "Project" -> {
                return new Project(nameableName);
            }
            case "Column" -> {
                return new Column(nameableName);
            }
            case "Task" -> {
                return new Task(nameableName);
            }
        }
        return null;
    }
}

