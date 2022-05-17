package app.smartboard.model.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
