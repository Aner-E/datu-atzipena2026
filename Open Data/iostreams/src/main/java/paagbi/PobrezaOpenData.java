package paagbi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class PobrezaOpenData {

        ArrayList<String[]> Datuak = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        /*
         * Erabiltzaileari menu nagusia erakusten dio eta
         * aukeratutako aukera exekutatzen du.
         */
        public void menu() {

                int aukera = 0;

                while (aukera != 5) {

                        System.out.println();
                        System.out.println("===== Pobrezia Arriskua Europan =====");
                        System.out.println("1. Datu guztiak ikusi");
                        System.out.println("2. Gizonen datuak ikusi");
                        System.out.println("3. Emakumeen datuak ikusi");
                        System.out.println("4. Herrialdeaz bilatu");
                        System.out.println("5. Irten");
                        System.out.print("Aukera bat sartu: ");

                        aukera = sc.nextInt();
                        sc.nextLine();

                        switch (aukera) {

                                case 1:
                                        datu_guztiak();
                                        break;

                                case 2:
                                        gizon_datuak();
                                        break;

                                case 3:
                                        emakume_datuak();
                                        break;

                                case 4:
                                        herrialde_datuak();
                                        break;

                                case 5:
                                        System.out.println("Programa itxita.");
                                        break;

                                default:
                                        System.out.println(
                                                        "Ez da aukera hori onartzen.");
                        }
                }
        }

        /*
         * Datu guztiak erakusten ditu, filtrorik egin gabe.
         */
        public void datu_guztiak() {

                System.out.println();
                System.out.println("==============================================================");
                System.out.println("                     DATU GUZTIAK");
                System.out.println("==============================================================");
                System.out.printf("%-30s %-12s %-10s %-10s%n",
                                "Herrialdea", "Sexua", "Urtea", "Guztira");
                System.out.println("--------------------------------------------------------------");

                for (String[] dato : Datuak) {

                        System.out.printf("%-30s %-12s %-10s %-10s%n",
                                        dato[0], dato[1], dato[2], dato[3]);
                }

                System.out.println("==============================================================");
        }

        /*
         * Gizonen datuak bakarrik erakusten ditu.
         */
        /*
         * Gizonen datuak bakarrik erakusten ditu.
         */
        public void gizon_datuak() {

                System.out.println();
                System.out.println("==============================================================");
                System.out.println("                     GIZONEN DATUAK");
                System.out.println("==============================================================");
                System.out.printf("%-30s %-10s %-10s%n",
                                "Herrialdea", "Urtea", "Guztira");
                System.out.println("--------------------------------------------------------------");

                for (String[] dato : Datuak) {

                        if (dato[1].equals("Hombres")) {

                                System.out.printf("%-30s %-10s %-10s%n",
                                                dato[0], dato[2], dato[3]);
                        }
                }

                System.out.println("==============================================================");
        }

        /*
         * Emakumeen datuak bakarrik erakusten ditu.
         */
        public void emakume_datuak() {

                System.out.println();
                System.out.println("==============================================================");
                System.out.println("                   EMAKUMEEN DATUAK");
                System.out.println("==============================================================");
                System.out.printf("%-30s %-10s %-10s%n",
                                "Herrialdea", "Urtea", "Guztira");
                System.out.println("--------------------------------------------------------------");

                for (String[] dato : Datuak) {

                        if (dato[1].equals("Mujeres")) {

                                System.out.printf("%-30s %-10s %-10s%n",
                                                dato[0], dato[2], dato[3]);
                        }
                }

                System.out.println("==============================================================");
        }


        /*
         * Sartutako herrialdea bilatzen du eta bere datuak erakusten ditu.
         */
        public void herrialde_datuak() {

                System.out.print(
                                "Bilatu nahi duzun herrialdearen izena sartu: ");

                String pais = sc.nextLine();

                boolean encontrado = false;

                System.out.println();
                System.out.println("==============================================================");
                System.out.println("                     " + pais.toUpperCase());
                System.out.println("==============================================================");
                System.out.printf("%-30s %-12s %-10s %-10s%n",
                                "Herrialdea", "Sexua", "Urtea", "Guztira");
                System.out.println("--------------------------------------------------------------");

                for (String[] dato : Datuak) {

                        if (dato[0].equalsIgnoreCase(pais)) {

                                System.out.printf("%-30s %-12s %-10s %-10s%n",
                                                dato[0], dato[1], dato[2], dato[3]);

                                encontrado = true;
                        }
                }

                if (!encontrado) {

                        System.out.println();
                        System.out.println("Ez da herrialdea aurkitu.");
                }

                System.out.println("==============================================================");
        }

        /*
         * CSV fitxategiko datuak Internetetik irakurri
         * eta programaren menua abiarazten du.
         */
        public static void main(String[] args) {

                String fitxategia = "https://raw.githubusercontent.com/Aner-E/datu-atzipena2026/main/pobreza.csv";

                PobrezaOpenData programa = new PobrezaOpenData();

                BufferedReader irakurlea = null;

                try {

                        URI uri = URI.create(fitxategia);
                        URL url = uri.toURL();

                        irakurlea = new BufferedReader(
                                        new InputStreamReader(url.openStream()));

                        // Cabecera
                        irakurlea.readLine();

                        String lerroa;

                        while ((lerroa = irakurlea.readLine()) != null) {

                                String[] datuak = lerroa.split(";");

                                if (datuak.length >= 4) {

                                        programa.Datuak.add(datuak);
                                }
                        }

                        programa.menu();

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

                        programa.sc.close();
                }
        }
}







