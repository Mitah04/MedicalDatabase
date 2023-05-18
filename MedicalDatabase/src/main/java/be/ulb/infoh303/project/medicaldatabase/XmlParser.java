package be.ulb.infoh303.project.medicaldatabase;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlParser {

    public Element parse(String filename) {

        Element root = null;

        try {
            // Load the XML file
            File xmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Get the root element
            root = doc.getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }
}
