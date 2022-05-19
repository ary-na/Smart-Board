package app.smartboard.view;

import app.smartboard.model.Project;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ProjectView extends Tab {

    private final Project project;

    public ProjectView(Project project) {
        this.project = project;
        projectLayout();
    }

    private void projectLayout() {

        // Set tab name
        setText(this.project.getName());

        // Create scroll pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setMinHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMinWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.prefHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.prefWidth(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxHeight(Control.USE_COMPUTED_SIZE);
        scrollPane.setMaxWidth(Control.USE_COMPUTED_SIZE);

        // Set tab content
        setContent(scrollPane);

        // Create HBox
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(20));
        hBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMinWidth(Control.USE_COMPUTED_SIZE);
        hBox.prefHeight(Control.USE_COMPUTED_SIZE);
        hBox.prefWidth(Control.USE_COMPUTED_SIZE);
        hBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
        hBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        hBox.getStyleClass().add("hbox-project");

        // Set scroll pane content
        scrollPane.setContent(hBox);
    }
}
