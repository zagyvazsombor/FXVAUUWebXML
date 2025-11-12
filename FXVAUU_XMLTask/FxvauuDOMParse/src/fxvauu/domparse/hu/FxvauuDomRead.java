package fxvauu.domparse.hu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FxvauuDomRead {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("./FXVAUU_XMLTask/FXVAUU_XML.xml"); // <-- bemeneti XML
        File outFile = new File("./FXVAUU_XMLTask/FxvauuDomRead_output.txt"); // <-- kimeneti txt

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // XML beolvasása DOM-ba
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Író a fájlkimenethez
        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile, false))) {

            // Gyökér elem kiírása
            String root = doc.getDocumentElement().getNodeName();
            printlnBoth(pw, "Gyökér elem: " + root);
            printlnBoth(pw, "----------------------------------------");

            // ====== Motorok ======
            NodeList motors = doc.getElementsByTagName("motor");
            for (int i = 0; i < motors.getLength(); i++) {
                Node n = motors.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String mid = e.getAttribute("m_ID");
                    String nev = getText(e, "nev");
                    String ver = getText(e, "verzio");
                    String lic = getText(e, "licenc");
                    String ceg = getText(e, "ceg");

                    printlnBoth(pw, "motor azonosító: " + mid);
                    printlnBoth(pw, "név: " + nev);
                    printlnBoth(pw, "verzió: " + ver);
                    printlnBoth(pw, "licenc: " + lic);
                    printlnBoth(pw, "cég: " + ceg);
                }
            }

            // ====== Játékok ======
            NodeList jatekok = doc.getElementsByTagName("jatek");
            for (int i = 0; i < jatekok.getLength(); i++) {
                Node n = jatekok.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String jid = e.getAttribute("j_ID");
                    String mid = e.getAttribute("m_ID");
                    String cim = getText(e, "cim");
                    String megj = getText(e, "megjelenes");

                    printlnBoth(pw, "játék azonosító: " + jid);
                    printlnBoth(pw, "használt motor: " + mid);
                    printlnBoth(pw, "cím: " + cim);
                    printlnBoth(pw, "megjelenés: " + megj);

                    // Többször előforduló elemek (platform, műfaj)
                    NodeList plats = e.getElementsByTagName("platform");
                    for (int p = 0; p < plats.getLength(); p++) {
                        printlnBoth(pw, "platform: " + plats.item(p).getTextContent().trim());
                    }
                    NodeList mufajok = e.getElementsByTagName("mufaj");
                    for (int m = 0; m < mufajok.getLength(); m++) {
                        printlnBoth(pw, "műfaj: " + mufajok.item(m).getTextContent().trim());
                    }
                }
            }

            // ====== Leírások ======
            NodeList leirasok = doc.getElementsByTagName("leiras");
            for (int i = 0; i < leirasok.getLength(); i++) {
                Node n = leirasok.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String lid = e.getAttribute("l_ID");
                    String jid = e.getAttribute("j_ID");
                    printlnBoth(pw, "leírás azonosító: " + lid);
                    printlnBoth(pw, "játék azonosító: " + jid);

                    NodeList nyelvek = e.getElementsByTagName("nyelv");
                    for (int ny = 0; ny < nyelvek.getLength(); ny++) {
                        printlnBoth(pw, "nyelv: " + nyelvek.item(ny).getTextContent().trim());
                    }
                    printlnBoth(pw, "tartalom: " + getText(e, "tartalom"));
                    printlnBoth(pw, "korosztály: " + getText(e, "korosztaly"));
                }
            }

            // ====== Kiadók ======
            NodeList kiadok = doc.getElementsByTagName("kiado");
            for (int i = 0; i < kiadok.getLength(); i++) {
                Node n = kiadok.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String kid = e.getAttribute("k_ID");
                    printlnBoth(pw, "kiadó azonosító: " + kid);
                    printlnBoth(pw, "név: " + getText(e, "nev"));
                    printlnBoth(pw, "email: " + getText(e, "email"));
                    printlnBoth(pw, "alapítás: " + getText(e, "alapitas"));
                    printlnBoth(pw, "ország: " + getText(e, "orszag"));
                }
            }

            // ====== Kiadási adatok ======
            NodeList kiadasok = doc.getElementsByTagName("kiadas");
            for (int i = 0; i < kiadasok.getLength(); i++) {
                Node n = kiadasok.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String kid = e.getAttribute("k_ID");
                    String jid = e.getAttribute("j_ID");
                    printlnBoth(pw, "kiadó azonosító: " + kid);
                    printlnBoth(pw, "játék azonosító: " + jid);
                    printlnBoth(pw, "dátum: " + getText(e, "datum"));
                    printlnBoth(pw, "ár: " + getText(e, "ar"));
                }
            }

            // ====== Fejlesztők ======
            NodeList fejlesztok = doc.getElementsByTagName("fejleszto");
            for (int i = 0; i < fejlesztok.getLength(); i++) {
                Node n = fejlesztok.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String fid = e.getAttribute("f_ID");
                    printlnBoth(pw, "fejlesztő azonosító: " + fid);

                    // beágyazott <nev>
                    Element nev = (Element) e.getElementsByTagName("nev").item(0);
                    if (nev != null) {
                        printlnBoth(pw, "vezetéknév: " + getText(nev, "vezetek_nev"));
                        printlnBoth(pw, "keresztnév: " + getText(nev, "kereszt_nev"));
                    }

                    NodeList szerepkorok = e.getElementsByTagName("szerepkor");
                    for (int s = 0; s < szerepkorok.getLength(); s++) {
                        printlnBoth(pw, "szerepkör: " + szerepkorok.item(s).getTextContent().trim());
                    }
                    printlnBoth(pw, "tapasztalat (év): " + getText(e, "tapasztalat"));
                    printlnBoth(pw, "email: " + getText(e, "email"));
                }
            }

            // ====== Fejlesztési adatok ======
            NodeList fejlesztesek = doc.getElementsByTagName("fejlesztes");
            for (int i = 0; i < fejlesztesek.getLength(); i++) {
                Node n = fejlesztesek.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String fid = e.getAttribute("f_ID");
                    String jid = e.getAttribute("j_ID");
                    printlnBoth(pw, "fejlesztő azonosító: " + fid);
                    printlnBoth(pw, "játék azonosító: " + jid);
                    printlnBoth(pw, "feladat: " + getText(e, "feladat"));
                    printlnBoth(pw, "munkaóra: " + getText(e, "munkaora"));
                }
            }

            // ====== Tesztelők ======
            NodeList tesztelok = doc.getElementsByTagName("tesztelo");
            for (int i = 0; i < tesztelok.getLength(); i++) {
                Node n = tesztelok.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String tid = e.getAttribute("t_ID");
                    printlnBoth(pw, "tesztelő azonosító: " + tid);

                    Element nev = (Element) e.getElementsByTagName("nev").item(0);
                    if (nev != null) {
                        printlnBoth(pw, "vezetéknév: " + getText(nev, "vezetek_nev"));
                        printlnBoth(pw, "keresztnév: " + getText(nev, "kereszt_nev"));
                    }
                    printlnBoth(pw, "specializáció: " + getText(e, "specializacio"));
                    printlnBoth(pw, "tapasztalat (év): " + getText(e, "tapasztalat"));
                    printlnBoth(pw, "email: " + getText(e, "email"));
                }
            }

            // ====== Tesztelési adatok ======
            NodeList tesztelesek = doc.getElementsByTagName("teszteles");
            for (int i = 0; i < tesztelesek.getLength(); i++) {
                Node n = tesztelesek.item(i);
                printlnBoth(pw, "\nAktuális elem: " + n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    String tid = e.getAttribute("t_ID");
                    String jid = e.getAttribute("j_ID");
                    printlnBoth(pw, "tesztelő azonosító: " + tid);
                    printlnBoth(pw, "játék azonosító: " + jid);
                    printlnBoth(pw, "dátum: " + getText(e, "datum"));
                    printlnBoth(pw, "eredmény: " + getText(e, "eredmeny"));
                }
            }

            printlnBoth(pw, "\n--- Mentve fájlba: " + outFile.getName() + " ---");
        }
    }

    // ===== Segédfüggvények =====

    /** Első tagName gyermek szövegének visszaadása, ha nincs: üres string. */
    private static String getText(Element parent, String tagName) {
        if (parent == null)
            return "";
        NodeList nl = parent.getElementsByTagName(tagName);
        if (nl == null || nl.getLength() == 0)
            return "";
        return nl.item(0).getTextContent().trim();
    }

    /** Ugyanazt a sort kiírja a konzolra és a fájlba is. */
    private static void printlnBoth(PrintWriter pw, String line) {
        System.out.println(line);
        pw.println(line);
    }
}
