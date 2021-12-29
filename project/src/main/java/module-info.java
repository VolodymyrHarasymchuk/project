module main.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports main.project.menu;
    opens main.project.menu to javafx.fxml;
}