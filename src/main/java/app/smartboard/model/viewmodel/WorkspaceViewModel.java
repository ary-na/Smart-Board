package app.smartboard.model.viewmodel;

import javafx.beans.property.*;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;

public class WorkspaceViewModel {

    private final StringProperty userFirstName = new SimpleStringProperty();
    private final BooleanProperty emptyWorkspace = new SimpleBooleanProperty();
    private final ObjectProperty<Image> userImage = new SimpleObjectProperty<>();
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


    public boolean getEmptyWorkspace() {
        return emptyWorkspace.get();
    }

    public void setEmptyWorkspace(boolean emptyWorkspace) {
        this.emptyWorkspace.set(emptyWorkspace);
    }

    public BooleanProperty emptyWorkspaceProperty() {
        return emptyWorkspace;
    }


    public Image getUserImage() {
        return userImage.get();
    }

    public void setUserImage(Image imageProperty) {
        this.userImage.set(imageProperty);
    }

    public ObjectProperty<Image> userImageProperty() {
        return userImage;
    }


    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }
}
