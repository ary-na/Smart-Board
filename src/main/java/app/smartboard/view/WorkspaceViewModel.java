package app.smartboard.view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class WorkspaceViewModel {
    private final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    private final StringProperty userFirstName = new SimpleStringProperty();

    public String getUserFirstName() {
        return userFirstName.get();
    }

    public StringProperty userFirstNameProperty() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName.set(userFirstName);
    }

    public Image getImageProperty() {
        return imageProperty.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public void setImageProperty(Image imageProperty) {
        this.imageProperty.set(imageProperty);
    }

}
