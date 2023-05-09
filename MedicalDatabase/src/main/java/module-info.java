module be.ulb.infoh303.project.medicaldatabase {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens be.ulb.infoh303.project.medicaldatabase to javafx.fxml;
    exports be.ulb.infoh303.project.medicaldatabase;
}