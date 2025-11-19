package org.json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

public class JSONValidationFxvauu {

    public static void main(String[] args) {

        try {
            // ObjectMapper a JSON-ekhez
            ObjectMapper mapper = new ObjectMapper();

            // Schema betöltése resources-ből
            InputStream schemaStream = JSONValidationFxvauu.class
                    .getResourceAsStream("/orarendJSONSchemaFxvauu.json");
            if (schemaStream == null) {
                System.out.println("Schema fájl nem található: orarendJSONSchemaNeptunkod.json");
                return;
            }

            JsonSchemaFactory schemaFactory = JsonSchemaFactory
                    .getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = schemaFactory.getSchema(schemaStream);

            // JSON betöltése resources-ből
            InputStream jsonStream = JSONValidationFxvauu.class
                    .getResourceAsStream("/orarendFxvauu.json");
            if (jsonStream == null) {
                System.out.println("JSON fájl nem található: orarendNeptunkod.json");
                return;
            }

            JsonNode jsonNode = mapper.readTree(jsonStream);

            // VALIDÁLÁS
            Set<ValidationMessage> errors = schema.validate(jsonNode);

            if (errors.isEmpty()) {
                System.out.println("Validation: OK (a JSON megfelel a sémának).");
            } else {
                System.out.println("Validation: HIBÁS JSON!");
                for (ValidationMessage vm : errors) {
                    System.out.println(" - " + vm.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}