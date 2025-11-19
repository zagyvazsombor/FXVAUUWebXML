package xpathfxvauu;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

public class xPathModifyFxvauu_ {

    public static void main(String[] args) {

        try {
            // ===== XML betöltése =====
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("./FXVAUU_1112/xPathFXVAUU/orarendFXVAUU.xml"));
            Element root = doc.getDocumentElement();

            // ================================================================
            // 1) ÚJ ÓRA HOZZÁADÁSA
            // ================================================================
            Element newOra = doc.createElement("ora");
            newOra.setAttribute("id", "99");
            newOra.setAttribute("tipus", "előadás");

            Element targy = doc.createElement("targy");
            targy.setTextContent("Mesterséges intelligencia 2.");
            newOra.appendChild(targy);

            Element idopont = doc.createElement("idopont");
            Element nap = doc.createElement("nap");
            nap.setTextContent("Csütörtök");
            Element tol = doc.createElement("tol");
            tol.setTextContent("8");
            Element ig = doc.createElement("ig");
            ig.setTextContent("10");
            idopont.appendChild(nap);
            idopont.appendChild(tol);
            idopont.appendChild(ig);
            newOra.appendChild(idopont);

            Element helyszin = doc.createElement("helyszin");
            helyszin.setTextContent("In/200");
            newOra.appendChild(helyszin);

            Element oktato = doc.createElement("oktato");
            oktato.setTextContent("Dr. Kovács István");
            newOra.appendChild(oktato);

            Element szak = doc.createElement("szak");
            szak.setTextContent("Mérnökinformatikus");
            newOra.appendChild(szak);

            root.appendChild(newOra);

            // ================================================================
            // 2) EGY ÓRA HELYSZÍNÉNEK MÓDOSÍTÁSA (ID=03)
            // ================================================================
            NodeList orak = doc.getElementsByTagName("ora");
            for (int i = 0; i < orak.getLength(); i++) {
                Element o = (Element) orak.item(i);
                if (o.getAttribute("id").equals("03")) {
                    o.getElementsByTagName("helyszin")
                            .item(0)
                            .setTextContent("A1/999");
                }
            }

            // ================================================================
            // 3) EGY ÓRA TÖRLÉSE ID ALAPJÁN (ID=06)
            // ================================================================
            for (int i = 0; i < orak.getLength(); i++) {
                Element o = (Element) orak.item(i);
                if (o.getAttribute("id").equals("06")) {
                    root.removeChild(o);
                    break;
                }
            }

            // ================================================================
            // KIÍRÁS KONZOLRA + FÁJLBA
            // ================================================================
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            System.out.println("===== MÓDOSÍTOTT XML =====");
            transformer.transform(new DOMSource(doc), new StreamResult(System.out));

            transformer.transform(
                    new DOMSource(doc),
                    new StreamResult(new File("./FXVAUU_1112/xPathFXVAUU/orarendFXVAUU1.xml")));

            System.out.println("\nFájl mentve: orarendFXVAUU1.xml");

        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
