package be.ulb.infoh303.project.medicaldatabase;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbIntializer {

    private Connection connection;

    public void connectToDb(String username, String password){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to postgresql");
        } catch (SQLException e) {
            System.out.println("ERROR CONNECTING TO DB");
            throw new RuntimeException(e);
        }
    }

    public void addPatientsToTable(Element root) throws SQLException {
        Statement statement = connection.createStatement();
        // Traverse the tree using a for loop
        NodeList nodeList = root.getElementsByTagName("patient");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element patient = (Element) nodeList.item(i);
            BigInteger niss = new BigInteger(patient.getElementsByTagName("NISS").item(0).getTextContent().trim());
            String birithDate = patient.getElementsByTagName("date_de_naissance").item(0).getTextContent();
            int gender = Integer.parseInt(patient.getElementsByTagName("genre").item(0).getTextContent().trim());
            BigInteger inamiMedecin = new BigInteger(patient.getElementsByTagName("inami_medecin").item(0).getTextContent().trim());
            BigInteger inamiPharmacien = new BigInteger(patient.getElementsByTagName("inami_pharmacien").item(0).getTextContent().trim());
            String mail = patient.getElementsByTagName("mail").item(0).getTextContent().replace("'", "''");
            String lastName = patient.getElementsByTagName("nom").item(0).getTextContent().replace("'", "''");;
            String firstName = patient.getElementsByTagName("prenom").item(0).getTextContent().replace("'", "''");;
            String telephone = patient.getElementsByTagName("telephone").item(0).getTextContent();
            statement.executeUpdate("INSERT INTO public.\"Patients\" VALUES(" + niss + ",'" + birithDate + "'," + gender + "," + inamiMedecin + "," + inamiPharmacien + ",'" + mail + "','" + lastName + "','" + firstName + "','" + telephone + "');");

        }
    }

    public void addDoctorsToTable(Element root) throws SQLException {
        Statement statement = connection.createStatement();
        // Traverse the tree using a for loop
        NodeList nodeList = root.getElementsByTagName("medecin");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element doctor = (Element) nodeList.item(i);
            BigInteger inami = new BigInteger(doctor.getElementsByTagName("inami").item(0).getTextContent().trim());
            String mail = doctor.getElementsByTagName("mail").item(0).getTextContent().replace("'", "''");
            String lastName = doctor.getElementsByTagName("nom").item(0).getTextContent().replace("'", "''");;
            String speciality = doctor.getElementsByTagName("specialite").item(0).getTextContent();
            String telephone = doctor.getElementsByTagName("telephone").item(0).getTextContent();

            statement.executeUpdate("INSERT INTO public.\"Doctors\" VALUES(" + inami + ",'" + mail + "','" + lastName + "','" + speciality + "','" + telephone + "');");

        }
    }

    public void addPharmacistsToTable(Element root) throws SQLException {
        Statement statement = connection.createStatement();
        // Traverse the tree using a for loop
        NodeList nodeList = root.getElementsByTagName("pharmacien");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element pharamacist = (Element) nodeList.item(i);
            BigInteger inami = new BigInteger(pharamacist.getElementsByTagName("inami").item(0).getTextContent().trim());
            String mail = pharamacist.getElementsByTagName("mail").item(0).getTextContent().replace("'", "''");
            String lastName = pharamacist.getElementsByTagName("nom").item(0).getTextContent().replace("'", "''");;
            String telephone = pharamacist.getElementsByTagName("tel").item(0).getTextContent();

            statement.executeUpdate("INSERT INTO public.\"Pharmacists\" VALUES(" + inami + ",'" + mail + "','" + lastName + "','" + telephone + "');");

        }
    }

    public void addDiagnosticsToTable(Element root) throws SQLException {
        Statement statement = connection.createStatement();
        // Traverse the tree using a for loop
        NodeList nodeList = root.getElementsByTagName("diagnostique");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element diagnostic = (Element) nodeList.item(i);
            BigInteger niss = new BigInteger(diagnostic.getElementsByTagName("NISS").item(0).getTextContent().trim());
            String diagnosticDate = diagnostic.getElementsByTagName("date_diagnostic").item(0).getTextContent();
            String birithDate = diagnostic.getElementsByTagName("naissance").item(0).getTextContent();
            String pathology = diagnostic.getElementsByTagName("pathology").item(0).getTextContent().replace("'", "''");
            String speciality = diagnostic.getElementsByTagName("specialite").item(0).getTextContent();

            statement.executeUpdate("INSERT INTO public.\"Diagnostics\" VALUES(" + niss + ",'" + diagnosticDate + "','" + birithDate + "','" + pathology + "','" + speciality + "');");

        }
    }


    public void initDb() throws SQLException {
        String path = "../../MY_XML/";


        XmlParser parser = new XmlParser();
        Element root = null;

        root = parser.parse(path + "patient.xml");
        addPatientsToTable(root);
        root = parser.parse(path + "medecins.xml");
        //addDoctorsToTable(root);
        root = parser.parse(path+"pharmaciens.xml");
        //addPharmacistsToTable(root);
        root = parser.parse(path+"diagnostiques.xml");
        //addDiagnosticsToTable(root);

    }
}
