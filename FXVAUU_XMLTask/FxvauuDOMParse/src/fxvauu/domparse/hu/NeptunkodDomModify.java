package fxvauu.domparse.hu;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class NeptunkodDomModify {

    public static void main(String[] argv) {
        try {
            // A bemeneti XML fájl elérési útja
            File inputFile = new File("./FXVAUU_XMLTask/FXVAUU_XML.xml");

            // DOM parser létrehozása és dokumentum beolvasása
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(inputFile);

            // Gyökérelem normalizálása (szövegcsomók rendezése)
            doc.getDocumentElement().normalize();

            // Gyökérelem kiírása
            printHeader("Gyökér elem: " + doc.getDocumentElement().getNodeName());

            // 1) Motor módosítása: m1 motor verzióját 5.4-re állítjuk
            title("1) Motor módosítása (m_ID=m1 → verzio=5.4)");
            Element m1 = byAttr(doc, "motor", "m_ID", "m1");
            if (m1 != null) {
                // verzio elem szövegének átírása
                setText(m1, "verzio", "5.4");
                printMotor(m1);
            } else
                System.out.println("Nincs m1 motor.\n");

            // 2) Játék módosítása: j4 játékhoz új platform elem, ha nincs PS5
            title("2) Játék módosítása (j_ID=j4 → platform: PS5 hozzáadása)");
            Element j4 = byAttr(doc, "jatek", "j_ID", "j4");
            if (j4 != null) {
                // platform elem hozzáadása, ha még nem létezik ilyen értékkel
                addChildIfMissingWithText(doc, j4, "platform", "PS5");
                printJatek(j4);
            } else
                System.out.println("Nincs j4 játék.\n");

            // 3) Leírás módosítása: l1 leíráshoz új nyelv 'német', ha hiányzik
            title("3) Leírás módosítása (l_ID=l1 → nyelv: német hozzáadása)");
            Element l1 = byAttr(doc, "leiras", "l_ID", "l1");
            if (l1 != null) {
                // új nyelv elem felvétele, ha még nincs ilyen értékkel
                addChildIfMissingWithText(doc, l1, "nyelv", "német");
                printLeiras(l1);
            } else
                System.out.println("Nincs l1 leírás.\n");

            // 4) Tesztelő módosítása: t3 tesztelő tapasztalat növelése és specializáció
            // módosítása
            title("4) Tesztelő módosítása (t_ID=t3 → tapasztalat +1, specializáció finomítva)");
            Element t3 = byAttr(doc, "tesztelo", "t_ID", "t3");
            if (t3 != null) {
                // tapasztalat elem növelése numerikusan
                int exp = parseInt(getText(t3, "tapasztalat"), 0) + 1;
                setText(t3, "tapasztalat", String.valueOf(exp));

                // specializáció kibővítése
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

    // Fejléc kiírása formázott blokkal

    private static void printHeader(String title) {
        System.out.println("========================================");
        System.out.println(title);
        System.out.println("========================================");
        System.out.println();
    }

    // Módosítási blokkok címének kiírása
    private static void title(String t) {
        System.out.println("-------------- " + t + " --------------");
    }

    // Motor egy teljes egyedként történő kiírása
    private static void printMotor(Element motor) {
        System.out.println("motor [m_ID=" + motor.getAttribute("m_ID") + "]");
        System.out.println("  nev: " + getText(motor, "nev"));
        System.out.println("  verzio: " + getText(motor, "verzio"));
        System.out.println("  licenc: " + getText(motor, "licenc"));
        System.out.println("  ceg: " + getText(motor, "ceg"));
        System.out.println();
    }

    // Játék egy teljes egyedként történő kiírása, többértékű elemekkel együtt
    private static void printJatek(Element jatek) {
        System.out.println("jatek [j_ID=" + jatek.getAttribute("j_ID")
                + ", m_ID=" + jatek.getAttribute("m_ID") + "]");
        System.out.println("  cim: " + getText(jatek, "cim"));
        System.out.println("  megjelenes: " + getText(jatek, "megjelenes"));

        // platform elemek bejárása
        NodeList plats = jatek.getElementsByTagName("platform");
        for (int i = 0; i < plats.getLength(); i++) {
            System.out.println("  platform: " + plats.item(i).getTextContent().trim());
        }

        // műfaj elemek bejárása
        NodeList mufajok = jatek.getElementsByTagName("mufaj");
        for (int i = 0; i < mufajok.getLength(); i++) {
            System.out.println("  mufaj: " + mufajok.item(i).getTextContent().trim());
        }
        System.out.println();
    }

    // Leírás teljes kiírása
    private static void printLeiras(Element leiras) {
        System.out.println("leiras [l_ID=" + leiras.getAttribute("l_ID")
                + ", j_ID=" + leiras.getAttribute("j_ID") + "]");

        // több nyelv kiírása
        NodeList ny = leiras.getElementsByTagName("nyelv");
        for (int i = 0; i < ny.getLength(); i++) {
            System.out.println("  nyelv: " + ny.item(i).getTextContent().trim());
        }

        System.out.println("  tartalom: " + getText(leiras, "tartalom"));
        System.out.println("  korosztaly: " + getText(leiras, "korosztaly"));
        System.out.println();
    }

    // Tesztelő teljes kiírása összetett névelemmel
    private static void printTesztelo(Element t) {
        System.out.println("tesztelo [t_ID=" + t.getAttribute("t_ID") + "]");

        // összetett nev elem (vezetéknév + keresztnév)
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

    // Elem keresése tagnév és attribútum alapján
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

    // Egy adott tag szövegének lekérése
    private static String getText(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        return (nl.getLength() > 0) ? nl.item(0).getTextContent().trim() : "";
    }

    // Egy adott tag szövegének beállítása vagy létrehozása
    private static void setText(Element parent, String tag, String value) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) {

            // ha nincs ilyen elem, létrehozzuk
            Element child = parent.getOwnerDocument().createElement(tag);
            child.setTextContent(value);
            parent.appendChild(child);
        } else {
            // ha létezik, átírjuk a szövegét
            nl.item(0).setTextContent(value);
        }
    }

    // Gyerekelem hozzáadása, ha nem létezik ugyanazzal az értékkel
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

    // Biztonságos egész szám parszolás alapértelmezéssel
    private static int parseInt(String s, int def) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return def;
        }
    }
}
