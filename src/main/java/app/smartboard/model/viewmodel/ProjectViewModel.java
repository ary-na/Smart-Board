package app.smartboard.model.viewmodel;

import app.smartboard.view.ColumnView;
import app.smartboard.view.ProjectView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.LinkedList;

public class ProjectViewModel {

    private ObservableList<Tab> projectTabs = FXCollections.observableArrayList();
    private TabPane tabPane = new TabPane();

    public ObservableList<Tab> getProjectTabs() {
        return projectTabs;
    }

    public void setProjectTabs(ObservableList<Tab> projectTabs) {
        this.projectTabs = projectTabs;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }
}
