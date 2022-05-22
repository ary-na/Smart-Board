package app.smartboard.view;

import app.smartboard.model.Project;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ProjectView extends Tab {

    private final Project project;
    private final HBox columnContainer;
    private ObservableList<ColumnView> columnViews;

    public ProjectView(Project project) {
        this.project = project;
        this.columnContainer = new HBox(20);
        this.columnViews = FXCollections.observableArrayList();
        Bindings.bindContent(this.columnContainer.getChildren(), this.columnViews);
        projectLayout();
    }

    public ObservableList<ColumnView> getColumnViews() {
        return columnViews;
    }

    public void setColumnViews(ObservableList<ColumnView> columnViews) {
        this.columnViews = columnViews;
    }

    public void addColumnView(ColumnView columnView){
        this.columnViews.add(columnView);
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

        // Create column container
        this.columnContainer.setPadding(new Insets(20));
        this.columnContainer.setMinHeight(Control.USE_COMPUTED_SIZE);
        this.columnContainer.setMinWidth(Control.USE_COMPUTED_SIZE);
        this.columnContainer.prefHeight(Control.USE_COMPUTED_SIZE);
        this.columnContainer.prefWidth(Control.USE_COMPUTED_SIZE);
        this.columnContainer.setMaxHeight(Control.USE_COMPUTED_SIZE);
        this.columnContainer.setMaxWidth(Control.USE_COMPUTED_SIZE);
        this.columnContainer.getStyleClass().add("hbox-project");

        // Set scroll pane content
        scrollPane.setContent(this.columnContainer);

        this.columnContainer.getChildren().addAll(this.columnViews);
    }


}
