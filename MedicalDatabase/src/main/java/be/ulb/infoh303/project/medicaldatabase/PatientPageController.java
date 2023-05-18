package be.ulb.infoh303.project.medicaldatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PatientPageController extends Controller {


    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private StackPane contentArea;

    public void displayID(){
        idLabel.setText(id);
    }

    public void displayName() { nameLabel.setText(dbManager.getPatientName(id));}

    @FXML
    void information(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("patient-info.fxml"));
        Parent fxml = loader.load();

        PatientInfoController controller = loader.getController();
        controller.setDataBaseManager(dbManager);
        controller.setId(id);

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
