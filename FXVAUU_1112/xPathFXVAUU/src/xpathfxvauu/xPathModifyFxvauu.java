package xpathfxvauu;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathModifyFxvauu {

    public static void main(String[] args) {

        try {
            // DocumentBuilder létrehozása
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // XML fájl beolvasása
            Document document = documentBuilder.parse("studentFXVAUU.xml");

            // Az XML dokumentum normalizálása: eltávolítja a felesleges whitespace
            // karaktereket
            // és egységes szerkezetet biztosít
            document.getDocumentElement().normalize();

            // XPath létrehozása
            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList neptunKod;
            // Meg kell adni az elérési út kifejezést és a csomópont listát
            // String neptunkod = "class";

            // Lekérdezés 1
            System.out.print("====Példány eredeti adatokkal====\n");
            neptunKod = (NodeList) xPath.compile("class/student[@id='s01']")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {
                Node node = (Node) neptunKod.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }
            }

            System.out.print("====Példány módosítás utáni adatokkal (Keresztnév=Dezső)====\n");
            for (int i = 0; i < neptunKod.getLength(); i++) {
                Node node = (Node) neptunKod.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    element.getElementsByTagName("keresztnev").item(0).setTextContent("Dezső");
                    System.out.println(
                            "Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }
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
