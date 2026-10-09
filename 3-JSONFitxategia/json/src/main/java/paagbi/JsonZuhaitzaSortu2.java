package paagbi;

import jakarta.json.Json;
import jakarta.json.JsonObject;

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

        System.out.println(model.toString());
    }
}