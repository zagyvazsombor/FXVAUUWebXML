package xpathfxvauu;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathQueryFxvauu_ {

    public static void main(String[] args) {

        try {
            // ===== XML betöltése =====
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("orarendFXVAUU.xml"));
            doc.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();

            // =======================================================
            // 1) Összes tantárgy kiírása
            // =======================================================
            System.out.println("1) Tantárgyak listája:");
            NodeList tantargyLista = (NodeList) xpath.evaluate(
                    "/FXVAUU_orarend/ora/targy",
                    doc,
                    XPathConstants.NODESET);

            for (int i = 0; i < tantargyLista.getLength(); i++) {
                System.out.println(" - " + tantargyLista.item(i).getTextContent());
            }

            System.out.println("\n-------------------------------------\n");

            // =======================================================
            // 2) Hétfői órák (összes infóval)
            // =======================================================
            System.out.println("2) Hétfői órák:");

            NodeList hetfoOrak = (NodeList) xpath.evaluate(
                    "/FXVAUU_orarend/ora[idopont/nap='Hétfő']",
                    doc,
                    XPathConstants.NODESET);

            for (int i = 0; i < hetfoOrak.getLength(); i++) {
                Element ora = (Element) hetfoOrak.item(i);

                String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                String tol = ora.getElementsByTagName("tol").item(0).getTextContent();
                String ig = ora.getElementsByTagName("ig").item(0).getTextContent();
                String hely = ora.getElementsByTagName("helyszin").item(0).getTextContent();
                String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();

                System.out.println(" - " + targy + " (" + tol + ":00 - " + ig + ":00) | "
                        + hely + " | " + oktato);
            }

            System.out.println("\n-------------------------------------\n");

            // =======================================================
            // 3) Gyakorlat típusú órák ID-i
            // =======================================================
            System.out.println("3) Gyakorlat órák ID-i:");

            NodeList gyakorlatok = (NodeList) xpath.evaluate(
                    "/FXVAUU_orarend/ora[@tipus='gyakorlat']",
                    doc,
                    XPathConstants.NODESET);

            for (int i = 0; i < gyakorlatok.getLength(); i++) {
                Element ora = (Element) gyakorlatok.item(i);
                String id = ora.getAttribute("id");
                System.out.println(" - óra ID: " + id);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}