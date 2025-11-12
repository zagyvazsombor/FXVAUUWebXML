package fxvauu.domparse.hu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NeptunkodDOMQuery {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        // Ha máshonnan futtatod, igazítsd az útvonalat.
        File xmlFile = new File("./FXVAUU_XMLTask/FXVAUU_XML.xml");

        // DOM inicializálás és dokumentum beolvasás
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Fejléc
        printHeader("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        // ===== 1) Motorok nevei listában =====
        startBlock("1) Motorok nevei");
        List<String> motorNevek = new ArrayList<>();
        NodeList motorok = doc.getElementsByTagName("motor");
        for (int i = 0; i < motorok.getLength(); i++) {
            Node n = motorok.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                String nev = getText(e, "nev");
                if (!nev.isEmpty())
                    motorNevek.add(nev);
            }
        }
        System.out.println("Motorok: " + asList(motorNevek));
        endBlock(); // üres sor a blokk után

        // ===== 2) Játékok: cím + megjelenés éve =====
        startBlock("2) Játékok (cím + megjelenés)");
        NodeList jatekok = doc.getElementsByTagName("jatek");
        List<String> jatekLista = new ArrayList<>();
        for (int i = 0; i < jatekok.getLength(); i++) {
            Node n = jatekok.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                String cim = getText(e, "cim");
                String ev = getText(e, "megjelenes");
                if (!cim.isEmpty()) {
                    jatekLista.add(cim + (ev.isEmpty() ? "" : " (" + ev + ")"));
                }
            }
        }
        System.out.println("Játékok: " + asList(jatekLista));
        endBlock();

        // ===== 3) Fejlesztők teljes nevei =====
        startBlock("3) Fejlesztők teljes nevei");
        NodeList fejlesztok = doc.getElementsByTagName("fejleszto");
        List<String> devNevek = new ArrayList<>();
        for (int i = 0; i < fejlesztok.getLength(); i++) {
            Node n = fejlesztok.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                Element nev = (Element) e.getElementsByTagName("nev").item(0);
                String v = (nev != null) ? getText(nev, "vezeteknev") : "";
                String k = (nev != null) ? getText(nev, "keresztnev") : "";
                String teljes = (v + " " + k).trim();
                if (!teljes.isEmpty())
                    devNevek.add(teljes);
            }
        }
        System.out.println("Fejlesztők: " + asList(devNevek));
        endBlock();

        // ===== 4) Összetett: Kiadási adatok játékcímmel és kiadó nevével =====
        startBlock("4) Kiadások (játékcím + kiadó + dátum + ár)");
        // Előkészítünk egy "join"-t DOM-ból: j_ID->cím, k_ID->kiadó név
        Map<String, String> jatekCimById = new LinkedHashMap<>();
        for (int i = 0; i < jatekok.getLength(); i++) {
            Element e = (Element) jatekok.item(i);
            String jId = e.getAttribute("j_ID");
            String cim = getText(e, "cim");
            if (!jId.isEmpty())
                jatekCimById.put(jId, cim);
        }

        Map<String, String> kiadoNevById = new LinkedHashMap<>();
        NodeList kiadok = doc.getElementsByTagName("kiado");
        for (int i = 0; i < kiadok.getLength(); i++) {
            Element e = (Element) kiadok.item(i);
            String kId = e.getAttribute("k_ID");
            String nev = getText(e, "nev");
            if (!kId.isEmpty())
                kiadoNevById.put(kId, nev);
        }

        NodeList kiadasok = doc.getElementsByTagName("kiadas");
        for (int i = 0; i < kiadasok.getLength(); i++) {
            Element e = (Element) kiadasok.item(i);
            String kId = e.getAttribute("k_ID");
            String jId = e.getAttribute("j_ID");
            String datum = getText(e, "datum");
            String ar = getText(e, "ar");

            String cim = jatekCimById.getOrDefault(jId, "(ismeretlen játék)");
            String kiado = kiadoNevById.getOrDefault(kId, "(ismeretlen kiadó)");

            System.out.println("- " + cim + " | kiadó: " + kiado + " | dátum: " + datum + " | ár: " + ar);
        }
        endBlock();
    }

    // ===== Segédfüggvények a szép blokkos kiíráshoz =====

    private static void printHeader(String title) {
        System.out.println("========================================");
        System.out.println(title);
        System.out.println("========================================");
        System.out.println();
    }

    private static void startBlock(String title) {
        System.out.println("-------------- " + title + " --------------");
    }

    private static void endBlock() {
        System.out.println("------------------------------------------");
        System.out.println(); // üres sor a blokk után
    }

    // ===== DOM segédfüggvények =====

    /** Első 'tagName' gyermek szövegének visszaadása; ha nincs, üres string. */
    private static String getText(Element parent, String tagName) {
        if (parent == null)
            return "";
        NodeList nl = parent.getElementsByTagName(tagName);
        if (nl == null || nl.getLength() == 0)
            return "";
        return nl.item(0).getTextContent().trim();
    }

    /** Lista szépen: [a, b, c] */
    private static String asList(List<String> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i < items.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
