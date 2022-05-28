package app.smartboard.model.viewmodel;

import app.smartboard.model.Project;
import app.smartboard.view.ProjectView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.HashMap;

public class ProjectViewModel {

    private ObservableList<Tab> projectTabs = FXCollections.observableArrayList();
    private HashMap<Project, ProjectView> projectMap = new HashMap<>();
    private TabPane tabPane = new TabPane();

    public ObservableList<Tab> getProjectTabs() {
        return projectTabs;
    }

    public void setProjectTabs(ObservableList<Tab> projectTabs) {
        this.projectTabs = projectTabs;
    }

    public HashMap<Project, ProjectView> getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(HashMap<Project, ProjectView> projectMap) {
        this.projectMap = projectMap;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }
}
