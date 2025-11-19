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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathQueryFxvauu {

    public static void main(String[] args) {

        try {
            // DocumentBuilder létrehozása
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // XML fájl beolvasása
            Document document = documentBuilder.parse("./studentU7W4RZ.xml");

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
            System.out.print("====Lekérdezés 1====");
            neptunKod = (NodeList) xPath.compile("class/student")
                    .evaluate(document, XPathConstants.NODESET);

            // A for ciklus segítségével a NodeList csomópontjain végig kell iterálni
            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }
            }

            // Lekérdezés 1

            // Lekérdezés 2
            System.out.print("\n====Lekérdezés 2====");
            neptunKod = (NodeList) xPath.compile("class/student[@id='s02']")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 2

            // Lekérdezés 3

            System.out.print("\n====Lekérdezés 3====");
            neptunKod = (NodeList) xPath.compile("//student")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 3

            // Lekérdezés 4

            System.out.print("\n====Lekérdezés 4====");
            neptunKod = (NodeList) xPath.compile("class/student[2]")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 4

            // Lekérdezés 5

            System.out.print("\n====Lekérdezés 5====");
            neptunKod = (NodeList) xPath.compile("class/student[last()]")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 5

            // Lekérdezés 6

            System.out.print("\n====Lekérdezés 6====");
            neptunKod = (NodeList) xPath.compile("class/student[last()-1]")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 6

            // Lekérdezés 7

            System.out.print("\n====Lekérdezés 7====");
            neptunKod = (NodeList) xPath.compile("class/student[position()<3]")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 7

            // Lekérdezés 8

            System.out.print("\n====Lekérdezés 8====");
            neptunKod = (NodeList) xPath.compile("class/*")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 8

            // Lekérdezés 9

            System.out.print("\n====Lekérdezés 9====");
            neptunKod = (NodeList) xPath.compile("class/student[@*]")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 9

            // Lekérdezés 10

            System.out.print("\n====Lekérdezés 10====\n");
            neptunKod = (NodeList) xPath.compile("//* | //@* | //text()")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {
                Node n = neptunKod.item(i);

                switch (n.getNodeType()) {

                    case Node.ELEMENT_NODE:
                        System.out.println("Element: " + n.getNodeName());
                        break;

                    case Node.ATTRIBUTE_NODE:
                        Attr a = (Attr) n;
                        System.out.println("Attribute: " + a.getName() + " = " + a.getValue());
                        break;

                    case Node.TEXT_NODE:
                        String text = n.getTextContent().trim();
                        if (!text.isEmpty()) {
                            System.out.println("Text: " + text);
                        }
                        break;
                }
            }

            // Lekérdezés 10

            // Lekérdezés 11

            System.out.print("\n====Lekérdezés 11====");
            neptunKod = (NodeList) xPath.compile("class/student[kor>20]")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {

                    // A Node elemet Elementté konvertáljuk, hogy hozzáférjünk az elem metódusaihoz
                    Element element = (Element) node;

                    // az id attribútumot adja vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet adja vissza
                    System.out.println("Keresztnév: " +
                            element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet adja vissza
                    System.out.println("Vezetéknév: " +
                            element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet adja vissza
                    System.out.println("Becenév: " +
                            element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort adja vissza
                    System.out.println("Kor: " +
                            element.getElementsByTagName("kor").item(0).getTextContent());
                }

            }
            // Lekérdezés 11

            // Lekérdezés 12

            System.out.print("\n====Lekérdezés 12====\n");
            neptunKod = (NodeList) xPath.compile("class/student/vezeteknev | class/student/keresztnev")
                    .evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < neptunKod.getLength(); i++) {

                // Kivesszük a NodeList-ből az aktuális Node elemet
                Node node = neptunKod.item(i);
                Element element = (Element) node;

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vezeteknev")) {

                    System.out.println("Vezetéknév: " + node.getTextContent());
                }

                else if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("keresztnev")) {

                    System.out.println("Keresztnév: " + node.getTextContent());
                }

            }
            // Lekérdezés 12

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
