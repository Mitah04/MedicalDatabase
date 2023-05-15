module be.ulb.infoh303.project.medicaldatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens be.ulb.infoh303.project.medicaldatabase to javafx.fxml;
    exports be.ulb.infoh303.project.medicaldatabase;
}