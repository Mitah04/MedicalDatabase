package be.ulb.infoh303.project.medicaldatabase;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String id;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField idTxtField;

    @FXML
    private ComboBox<String> userroleComb;

    boolean verifyLogin(String userrole, String id){
        // TODO

        boolean res = false;

        if (userrole == "Patient")
            res = dbManager.verifyPatient(id);
        else if ( userrole == "Doctor")
            res = dbManager.verifyDoctor(id);
        else if ( userrole == "Pharamacist")
            res = dbManager.verifyPharmacist(id);

        return res;

    }

    public void login(ActionEvent event) throws IOException {
        id = idTxtField.getText();
        String userrole = userroleComb.getValue();

        if (verifyLogin(userrole, id)){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("patient-page.fxml"));
            root = loader.load();

           PatientPageController controller = loader.getController();
           controller.setDataBaseManager(dbManager);
           controller.setId(id);
           controller.displayID();
           controller.displayName();




            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            errorLabel.setVisible(true);
        }
    }

    public void register(ActionEvent event) throws IOException{

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userroleComb.setItems(FXCollections.observableArrayList("Patient","Doctor","Pharmacist"));
    }
}