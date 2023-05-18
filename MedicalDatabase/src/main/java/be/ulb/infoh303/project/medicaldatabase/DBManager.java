package be.ulb.infoh303.project.medicaldatabase;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.math.BigInteger;
import java.net.ConnectException;
import java.sql.*;

public class DBManager {

    private Connection connection;
    private Statement statement;

    public void connectToDb(String username, String password){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            System.out.println("Connected to postgresql");
        } catch (SQLException e) {
            System.out.println("ERROR CONNECTING TO DB");
            throw new RuntimeException(e);
        }
    }


    public void displayMedecins(String dci) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT m.\"Commercial_Name\" FROM public.\"Medecines\" AS m WHERE m.\"DCI\" = " + "'" + dci + "'" + "ORDER BY m.\"Packaging\";");

        while (res.next()){
            String commercialName = res.getString("Commercial_Name");

            System.out.println(commercialName);
        }
    }

    public void availablePathology(String speciality) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT p.\"pathology\" FROM public.\"Pathologies\" AS p, public.\"Doctors\" AS d WHERE p.\"speciality\" = " + "'" + speciality + "';");

        while (res.next()){
            String commercialName = res.getString("pathology");

            System.out.println(commercialName);
        }

    }

    public void mostFrequentPathology() throws SQLException {
        ResultSet res = statement.executeQuery("SELECT COUNT(pathology) AS cnt, pathology FROM public.\"Diagnostics\"  GROUP BY pathology ORDER BY cnt DESC LIMIT 1;");
        res.next();
        System.out.println(res.getString("pathology"));
    }


    public boolean verifyPharmacist(String id) {
        boolean res = false;
        try {
            ResultSet rslt = statement.executeQuery("SELECT p.\"inami\" FROM public.\"Pharmacists\" AS p WHERE p.\"niss\" = " + "'" + id + "';");
            res = rslt.next();
        } catch (SQLException e) {
        }

        return res;
    }

    public boolean verifyDoctor(String id) {
        boolean res = false;
        try {
            ResultSet rslt = statement.executeQuery("SELECT p.\"inami\" FROM public.\"Doctors\" AS p WHERE p.\"niss\" = " + "'" + id + "';");
            res = rslt.next();
        } catch (SQLException e) {
        }

        return res;
    }

    public boolean verifyPatient(String id) {
        boolean res = false;
        try {
            ResultSet rslt = statement.executeQuery("SELECT p.\"niss\" FROM public.\"Patients\" AS p WHERE p.\"niss\" = " + "'" + id + "';");
            res = rslt.next();
        } catch (SQLException e) {
        }

        return res;


    }

    public String getPatientName(String id) {
        String name = "";
        try {
            ResultSet rslt = statement.executeQuery("SELECT p.\"first_name\",p.\"last_name\" FROM public.\"Patients\" AS p WHERE p.\"niss\" = " + "'" + id + "';");
            rslt.next();
            name += rslt.getString("first_name").trim() + " " + rslt.getString("last_name").trim();

        } catch (SQLException e) {
        }
        return name;
    }

    public ResultSet getPatientInfo(String id){
        ResultSet rslt = null;
        try {
            rslt = statement.executeQuery("SELECT * FROM public.\"Patients\" AS p WHERE p.\"niss\" = " + "'" + id + "';");
        } catch (SQLException e) {
        }
        return rslt;

    }
}
