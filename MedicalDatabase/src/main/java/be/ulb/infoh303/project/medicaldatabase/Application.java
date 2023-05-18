package be.ulb.infoh303.project.medicaldatabase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {


        DBManager dbManager = new DBManager();

        dbManager.connectToDb("postgres", "1234");

        FXMLLoader loader= new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setDataBaseManager(dbManager);

        stage.setTitle("Patient Database");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}