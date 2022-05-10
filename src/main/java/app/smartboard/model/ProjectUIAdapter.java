package app.smartboard.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ProjectUIAdapter extends Tab {

    private final Project project;
    private final StringProperty projectName;

    public ProjectUIAdapter(Project project) {
        this.project = project;
        this.projectName = new SimpleStringProperty();
        bindToModel();
        layout();
    }

    public void bindToModel(){
        this.projectName.bindBidirectional(this.project.nameProperty());
    }

    private void layout(){
        this.setText(project.getName());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMinHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMinWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.prefHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.prefWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxWidth(Control.USE_COMPUTED_SIZE);

        setContent(scrollPane);

        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(20));
        hBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        hBox.prefHeight(Control.USE_COMPUTED_SIZE);
        hBox.prefWidth(Control.USE_COMPUTED_SIZE);
        hBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        hBox.getStyleClass().add("hbox-project");

        scrollPane.setContent(hBox);
    }
}
