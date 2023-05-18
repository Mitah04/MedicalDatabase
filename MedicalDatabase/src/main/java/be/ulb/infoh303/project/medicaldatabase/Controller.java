package be.ulb.infoh303.project.medicaldatabase;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {

    protected DBManager dbManager;

    protected String id;

    public void setDataBaseManager(DBManager manager) {
        dbManager = manager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Nothing
    }

    protected void setId(String id){ this.id = id;}
}
