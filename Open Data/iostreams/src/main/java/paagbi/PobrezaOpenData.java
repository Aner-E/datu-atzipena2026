package paagbi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class PobrezaOpenData {

    public static void main(String[] args) {

        String fitxategia =
                "https://www.ine.es/jaxiT3/files/t/csv_bdsc/11182.csv";

        BufferedReader irakurlea = null;

        try {

            URI uri = URI.create(fitxategia);
            URL url = uri.toURL(); 

            irakurlea = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            irakurlea.readLine();

            String lerroa;

            while ((lerroa = irakurlea.readLine()) != null) {

                String[] datuak = lerroa.split(";");

                String herrialdea = datuak[0];
                String sexua = datuak[1];
                String urtea = datuak[2];
                String total = datuak[3];

                System.out.println(
                        herrialdea + " | " + sexua + " | " + urtea + " | " + total);

                if (total.equals("\"\"") || total.equals("..")) {
                    continue;
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Errorea fitxategia irakurtzean: "
                    + e.getMessage());

        } finally {

            try {

                if (irakurlea != null) {
                    irakurlea.close();
                }

            } catch (IOException e) {

                System.out.println(
                        "Errorea fitxategia ixtean.");
            }
        }
    }
}