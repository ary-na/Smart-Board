package app.smartboard.model.viewmodel;

import javafx.beans.property.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;

public class WorkspaceViewModel {
    private final StringProperty userFirstName = new SimpleStringProperty();
    private final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    private final BooleanProperty projectCreated = new SimpleBooleanProperty();
    private TabPane tabPane = new TabPane();

    public String getUserFirstName() {
        return userFirstName.get();
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName.set(userFirstName);
    }

    public StringProperty userFirstNameProperty() {
        return userFirstName;
    }

    public Image getImageProperty() {
        return imageProperty.get();
    }

    public void setImageProperty(Image imageProperty) {
        this.imageProperty.set(imageProperty);
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public boolean isProjectCreated() {
        return projectCreated.get();
    }

    public void setProjectCreated(boolean projectCreated) {
        this.projectCreated.set(projectCreated);
    }

    public BooleanProperty projectCreatedProperty() {
        return projectCreated;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }
}
