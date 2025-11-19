package fxvauuJSON;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONWriteFxvauu {

    public static void main(String[] args) {

        String inputPath = "FXVAUU_1119/orarendFxvauu.json";
        String outputPath = "FXVAUU_1119/orarendFxvauu1.json";

        try (FileReader reader = new FileReader(inputPath)) {

            // JSON beolvasás és parse
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            // Gyökérelem: FXVAUU_orarend
            JSONObject root = (JSONObject) jsonObject.get("FXVAUU_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            System.out.println("FXVAUU Órarend 2025 ősz (konzol + fájl):\n");

            // Új JSON struktúra felépítése a kimeneti fájlhoz
            JSONObject outRootWrapper = new JSONObject(); // külső objektum
            JSONObject outSchedule = new JSONObject(); // FXVAUU_orarend objektum
            JSONArray outLessons = new JSONArray(); // órák tömbje

            for (int i = 0; i < lessons.size(); i++) {
                JSONObject lesson = (JSONObject) lessons.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");

                // Konzolra kiírás blokk formában
                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + ", "
                        + time.get("tol") + " - " + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
                System.out.println("-------------------------");

                // Új óra objektum a kimeneti JSON-hoz
                JSONObject outLesson = new JSONObject();
                outLesson.put("tipus", lesson.get("tipus"));
                outLesson.put("targy", lesson.get("targy"));
                outLesson.put("idopont", time); // időpontot egyben tesszük be
                outLesson.put("helyszin", lesson.get("helyszin"));
                outLesson.put("oktato", lesson.get("oktato"));
                outLesson.put("szak", lesson.get("szak"));

                outLessons.add(outLesson);
            }

            // Teljes kimeneti JSON struktúra összerakása
            outSchedule.put("ora", outLessons);
            outRootWrapper.put("FXVAUU_orarend", outSchedule);

            // Kimeneti JSON fájlba írás
            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(outRootWrapper.toJSONString());
            }

            System.out.println("\nKimeneti JSON fájl létrehozva: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
