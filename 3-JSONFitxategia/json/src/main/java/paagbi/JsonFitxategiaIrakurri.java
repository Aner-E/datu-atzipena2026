package paagbi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;

/**
 * Hello world!
 *
 */
public class JsonFitxategiaIrakurri 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        JsonReader reader = Json.createReader(new FileReader("data/test.json"));
        JsonStructure jsonst = reader.read();

        System.out.println(jsonst.getValueType());
        System.out.println(jsonst);
    }
}
