package app.smartboard.model;

import java.io.Serializable;

public abstract class Nameable implements Serializable {
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
