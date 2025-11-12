package fxvauu.domparse.hu;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class NeptunkodDomModify {

    public static void main(String[] argv) {
        try {
            // ha máshonnan futtatod, ezt az elérési utat igazítsd
            File inputFile = new File("./FXVAUU_XMLTask/FXVAUU_XML.xml");

            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Győkérelem: " + doc.getDocumentElement().getNodeName() + "\n");

            // 1) Motor (m_ID=m1): verzió frissítése 5.4-re
            title("1) Motor módosítása (m_ID=m1 → verzio=5.4)");
            Element m1 = byAttr(doc, "motor", "m_ID", "m1");
            if (m1 != null) {
                setText(m1, "verzio", "5.4");
                printMotor(m1);
            } else
                System.out.println("Nincs m1 motor.\n");

            // 2) Játék (j_ID=j4): új platform PS5 hozzáadása, ha hiányzik
            title("2) Játék módosítása (j_ID=j4 → platform: PS5 hozzáadása)");
            Element j4 = byAttr(doc, "jatek", "j_ID", "j4");
            if (j4 != null) {
                addChildIfMissingWithText(doc, j4, "platform", "PS5");
                printJatek(j4);
            } else
                System.out.println("Nincs j4 játék.\n");

            // 3) Leírás (l_ID=l1): új nyelv „német” hozzáadása, ha hiányzik
            title("3) Leírás módosítása (l_ID=l1 → nyelv: német hozzáadása)");
            Element l1 = byAttr(doc, "leiras", "l_ID", "l1");
            if (l1 != null) {
                addChildIfMissingWithText(doc, l1, "nyelv", "német");
                printLeiras(l1);
            } else
                System.out.println("Nincs l1 leírás.\n");

            // 4) Tesztelő (t_ID=t3): tapasztalat +1 év, specializáció finomhangolás
            title("4) Tesztelő módosítása (t_ID=t3 → tapasztalat +1, specializáció finomítva)");
            Element t3 = byAttr(doc, "tesztelo", "t_ID", "t3");
            if (t3 != null) {
                // tapasztalat növelése
                int exp = parseInt(getText(t3, "tapasztalat"), 0) + 1;
                setText(t3, "tapasztalat", String.valueOf(exp));
                // specializáció átírása informatívabbra
                String spec = getText(t3, "specializacio");
                if (spec.isEmpty())
                    spec = "Grafikai hibák";
                setText(t3, "specializacio", spec + " és UX ellenőrzés");
                printTesztelo(t3);
            } else
                System.out.println("Nem találtam t3 tesztelőt.\n");

            System.out.println("=== Kész ===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------- Blokkfejlécek ----------
    private static void title(String t) {
        System.out.println("-------------- " + t + " --------------");
    }

    // ---------- Teljes egyedek kiírása ----------
    private static void printMotor(Element motor) {
        System.out.println("motor [m_ID=" + motor.getAttribute("m_ID") + "]");
        System.out.println("  nev: " + getText(motor, "nev"));
        System.out.println("  verzio: " + getText(motor, "verzio"));
        System.out.println("  licenc: " + getText(motor, "licenc"));
        System.out.println("  ceg: " + getText(motor, "ceg"));
        System.out.println();
    }

    private static void printJatek(Element jatek) {
        System.out.println("jatek [j_ID=" + jatek.getAttribute("j_ID")
                + ", m_ID=" + jatek.getAttribute("m_ID") + "]");
        System.out.println("  cim: " + getText(jatek, "cim"));
        System.out.println("  megjelenes: " + getText(jatek, "megjelenes"));
        NodeList plats = jatek.getElementsByTagName("platform");
        for (int i = 0; i < plats.getLength(); i++) {
            System.out.println("  platform: " + plats.item(i).getTextContent().trim());
        }
        NodeList mufajok = jatek.getElementsByTagName("mufaj");
        for (int i = 0; i < mufajok.getLength(); i++) {
            System.out.println("  mufaj: " + mufajok.item(i).getTextContent().trim());
        }
        System.out.println();
    }

    private static void printLeiras(Element leiras) {
        System.out.println("leiras [l_ID=" + leiras.getAttribute("l_ID")
                + ", j_ID=" + leiras.getAttribute("j_ID") + "]");
        NodeList ny = leiras.getElementsByTagName("nyelv");
        for (int i = 0; i < ny.getLength(); i++) {
            System.out.println("  nyelv: " + ny.item(i).getTextContent().trim());
        }
        System.out.println("  tartalom: " + getText(leiras, "tartalom"));
        System.out.println("  korosztaly: " + getText(leiras, "korosztaly"));
        System.out.println();
    }

    private static void printTesztelo(Element t) {
        System.out.println("tesztelo [t_ID=" + t.getAttribute("t_ID") + "]");
        Element nev = (Element) t.getElementsByTagName("nev").item(0);
        if (nev != null) {
            System.out.println("  vezeteknev: " + getText(nev, "vezeteknev"));
            System.out.println("  keresztnev: " + getText(nev, "keresztnev"));
        }
        System.out.println("  specializacio: " + getText(t, "specializacio"));
        System.out.println("  tapasztalat: " + getText(t, "tapasztalat"));
        System.out.println("  email: " + getText(t, "email"));
        System.out.println();
    }

    // ---------- DOM segédfüggvények ----------
    private static Element byAttr(Document doc, String tag, String attr, String val) {
        NodeList nl = doc.getElementsByTagName(tag);
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;
                if (val.equals(e.getAttribute(attr)))
                    return e;
            }
        }
        return null;
    }

    private static String getText(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        return (nl.getLength() > 0) ? nl.item(0).getTextContent().trim() : "";
    }

    private static void setText(Element parent, String tag, String value) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) {
            Element child = parent.getOwnerDocument().createElement(tag);
            child.setTextContent(value);
            parent.appendChild(child);
        } else {
            nl.item(0).setTextContent(value);
        }
    }

    private static void addChildIfMissingWithText(Document doc, Element parent, String tag, String text) {
        NodeList nl = parent.getElementsByTagName(tag);
        for (int i = 0; i < nl.getLength(); i++) {
            if (text.equalsIgnoreCase(nl.item(i).getTextContent().trim()))
                return; // már van
        }
        Element child = doc.createElement(tag);
        child.setTextContent(text);
        parent.appendChild(child);
    }

    private static int parseInt(String s, int def) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return def;
        }
    }
}
