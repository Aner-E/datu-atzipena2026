package paagbi;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.stream.JsonGenerator;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonZuhaitzaSortu2 {
    public static void main(String[] args) {
        JsonObject model = Json.createObjectBuilder()
            .add("menu", Json.createObjectBuilder()
                .add("id", "31")
                .add("value", "23€")
                .add("popup", Json.createObjectBuilder()
                    .add("menuitem", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                            .add("value", "New")
                            .add("onclick", "CreateNewDoc()"))
                        .add(Json.createObjectBuilder()
                            .add("value", "Open")
                            .add("onclick", "OpenDoc()"))
                        .add(Json.createObjectBuilder()
                            .add("value", "Close")
                            .add("onclick", "CloseDoc()")))))
            .build();

        // Pretty printing konfiguratu
        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        StringWriter stringWriter = new StringWriter();

        try (JsonWriter jsonWriter = writerFactory.createWriter(stringWriter)) {
            jsonWriter.writeObject(model);
        }

        System.out.println(stringWriter.toString());
    }
}