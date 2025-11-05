package domfxvauu1029;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DOMWrite {

    private static final String INPUT_XML = "FXVAUUhallgato.xml";
    private static final String OUTPUT_XML = "FXVAUUhallgato1.xml";

    public static void main(String[] args) {
        try {
            // 1) DOM létrehozása és XML beolvasása
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true); // névterek kezelése (általános jó gyakorlat)
            // Ha whitespace normalizálásra szükség van DTD mellett:
            // dbf.setIgnoringElementContentWhitespace(true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            File in = new File(INPUT_XML);
            if (!in.exists()) {
                System.err.println("Nem található a bemeneti XML: " + in.getAbsolutePath());
                System.exit(1);
            }

            Document doc = db.parse(in);
            doc.getDocumentElement().normalize();

            // 2) Transformer beállítása „szépen formázott” (indented) XML kimenethez
            TransformerFactory tf = TransformerFactory.newInstance();
            try {
                // Nem minden implementáció támogatja, de ha igen, szebb lesz a behúzás.
                tf.setAttribute("indent-number", 2);
            } catch (IllegalArgumentException ignore) {
                // Ha nem támogatja, nincs gond, lentebb az Xalan-összetevőt is megadjuk.
            }

            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // Xalan-specifikus extra, sok JDK alatt működik:
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);

            // 3) Kiírás KONZOLRA (blokk/pretty-printed XML)
            System.out.println("=== " + INPUT_XML + " (szépen formázott) ===");
            transformer.transform(source, new StreamResult(System.out));

            // 4) Kiírás FÁJLBA (hallgato1Neptunkod.xml)
            try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(OUTPUT_XML),
                    StandardCharsets.UTF_8)) {
                transformer.transform(source, new StreamResult(out));
            }

            System.out.println();
            System.out.println("Kész. Kimeneti fájl: " + new File(OUTPUT_XML).getAbsolutePath());

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
            System.err.println("Hiba történt a feldolgozás során: " + e.getMessage());
        }
    }
}
