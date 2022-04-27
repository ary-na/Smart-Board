package app.smartboard.model;

public abstract class Nameable {
    private String name;

    public Nameable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
