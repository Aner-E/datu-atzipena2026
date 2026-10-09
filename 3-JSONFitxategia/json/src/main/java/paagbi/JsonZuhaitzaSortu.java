package paagbi;

import java.io.FileNotFoundException;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JsonZuhaitzaSortu {
    public static void main(String[] args) throws FileNotFoundException {
        JsonObject model = Json.createObjectBuilder()
            .add("firstName", "Aner")
            .add("lastName", "Esmeralda")
            .add("age", 19)
            .add("streetAddress", "100 Internet Dr")
            .add("city", "JavaTown")
            .add("state", "JA")
            .add("postalCode", "12345")
            .add("phoneNumbers", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                    .add("type", "mobile")
                    .add("number", "111-111-1111"))
                .add(Json.createObjectBuilder()
                    .add("type", "home")
                    .add("number", "222-222-2222")))
            .build(); 

        System.out.println(model.toString());
    }
}