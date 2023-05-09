package be.ulb.infoh303.project.medicaldatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PatientPageController {

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private StackPane contentArea;

    public void displayID(String id){
        idLabel.setText(id);
    }

    @FXML
    void information(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("patient-info.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void treatments(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("patient-treatments.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

}
