package be.ulb.infoh303.project.medicaldatabase;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DBManager manager = new DBManager();
        manager.connectToDb("postgres", "1234");
        manager.mostFrequentPathology();

    }
}
