package app.smartboard.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerHelper {

    private static ControllerHelper controllerHelperInstance;
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<javafx.scene.image.Image> image = new SimpleObjectProperty<>();

    private ControllerHelper() {
    }

    public synchronized static ControllerHelper getControllerHelperInstance() {
        if (controllerHelperInstance == null)
            controllerHelperInstance = new ControllerHelper();
        return controllerHelperInstance;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public Image getImage() {
        return image.get();
    }

    public void setImage(Image image){
        this.image.set(image);
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }
}
