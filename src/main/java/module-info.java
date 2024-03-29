module app.smartboard {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires junit;

    opens app.smartboard to javafx.fxml;
    exports app.smartboard;
    exports app.smartboard.model;
    opens app.smartboard.model to javafx.fxml;
    exports app.smartboard.controller;
    opens app.smartboard.controller to javafx.fxml;
    exports app.smartboard.model.database;
    opens app.smartboard.model.database to javafx.fxml;
    exports app.smartboard.view;
    exports app.smartboard.model.viewmodel;
    opens app.smartboard.view to javafx.fxml;
    exports app.smartboard.model.test;
    opens app.smartboard.model.test to javafx.fxml;
}