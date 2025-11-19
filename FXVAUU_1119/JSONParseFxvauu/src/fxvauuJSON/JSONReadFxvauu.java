package fxvauuJSON;

public class JSONReadFxvauu {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("././orarendFxvauu.json")) {
            // parse
            JSONParser JSONParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) JSONParser.parse(reader);

            JSONObject root = (JSONObject) jsonObject.get("FXVAUU_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            System.out.println("FXVAUU Órarend 2025 ősz: \n");

            for (int i = 0; i < lessons.size(); i++) {
                JSONObject lesson = (JSONObject) lessons.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Időpont: " + time.get("nap") + ", " + time.get("tol") + " - " + time.get("ig"));
                System.out.println("helyszín: " + lesson.get("helyszin"));
                System.out.println("oktató: " + lesson.get("oktato"));
                System.out.println("szak: " + lesson.get("szak"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}