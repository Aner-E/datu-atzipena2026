package paagbi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PobrezaOpenData {

    public static void main(String[] args) {

        String fitxategia =
                "https://www.ine.es/jaxiT3/files/t/csv_bdsc/11182.csv";

        ArrayList<String[]> Datuak = new ArrayList<>();

        BufferedReader irakurlea = null;
        Scanner sc = new Scanner(System.in);

        try {

            URI uri = URI.create(fitxategia);
            URL url = uri.toURL();

            irakurlea = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            irakurlea.readLine();

            String lerroa;

            while ((lerroa = irakurlea.readLine()) != null) {

                String[] datuak = lerroa.split(";");

                if (datuak.length >= 4) {
                    Datuak.add(datuak);
                }
            }

            int aukera = 0;

            while (aukera != 5) {

                System.out.println();
                System.out.println("===== MENU =====");
                System.out.println("1. Datu gustiak ikusi");
                System.out.println("2. Gizonen datuak ikusi");
                System.out.println("3. Emakumeen datuak ikusi");
                System.out.println("4. Herrialdeaz bilatu");
                System.out.println("5. Irten");
                System.out.print("Aukera bat sartu: ");

                aukera = sc.nextInt();
                sc.nextLine();

                switch (aukera) {

                    case 1:

                        System.out.println();
                        System.out.println("===== Datu Gustiak =====");

                        for (String[] dato : Datuak) {

                            System.out.println(dato[0] + " | " + dato[1] + " | " + dato[2] + " | " + dato[3]);
                        }

                        break;

                    case 2:

                        System.out.println();
                        System.out.println("===== Gizonen Datuak =====");

                        for (String[] dato : Datuak) {

                            if (dato[1].equals("Hombres")) {

                                System.out.println(dato[0] + " | " + dato[2] + " | " + dato[3]);
                            }
                        }

                        break;

                    case 3:

                        System.out.println();
                        System.out.println("===== Emakumeen Datuak =====");

                        for (String[] dato : Datuak) {

                            if (dato[1].equals("Mujeres")) {

                                System.out.println(dato[0] + " | " + dato[2] + " | " + dato[3]);
                            }
                        }

                        break;

                    case 4:

                        System.out.print(
                                "Bilatu nahi duzun herrialdearen izena sartu: ");

                        String pais = sc.nextLine();

                        boolean encontrado = false;

                        System.out.println();
                        System.out.println("===== RESULTADOS PARA " + pais + " =====");

                        for (String[] dato : Datuak) {

                            if (dato[0].equalsIgnoreCase(pais)) {

                                System.out.println(dato[0] + " | " + dato[1] + " | " + dato[2] + " | " + dato[3]);

                                encontrado = true;
                            }
                        }

                        if (!encontrado) {

                            System.out.println("Ez da herrialdea aurkitu.");
                        }

                        break;

                    case 5:

                        System.out.println("Programa itxita.");

                        break;

                    default:

                        System.out.println("Ez da aukera hori onartzen.");
                }
            }

        } catch (IOException e) {

            System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());

        } finally {

            try {

                if (irakurlea != null) {
                    irakurlea.close();
                }

            } catch (IOException e) {

                System.out.println(
                        "Errorea fitxategia ixtean.");
            }

            sc.close();
        }
    }
}