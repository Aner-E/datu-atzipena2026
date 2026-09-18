package paagbi;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PobrezaOpenData {

    public static void main(String[] args) {

        String fitxategia = "pobreza.csv";

        double gizonenBatura = 0;
        double emakumeenBatura = 0;
        int herrialdeKopurua = 0;

        String herrialdeGehiena = "";
        double ehunekoGehiena = 0;

        FileInputStream fis = null;
        BufferedReader irakurlea = null;

        try {

            fis = new FileInputStream(fitxategia);

            irakurlea = new BufferedReader(
                    new InputStreamReader(fis));

            irakurlea.readLine();

            String lerroa;

            while ((lerroa = irakurlea.readLine()) != null) {

                String[] datuak = lerroa.split(",");

                String herrialdea = datuak[2];

                double gizonak =
                        Double.parseDouble(datuak[3]);

                double emakumeak =
                        Double.parseDouble(datuak[4]);

                gizonenBatura += gizonak;
                emakumeenBatura += emakumeak;

                herrialdeKopurua++;

                double batezBestekoa =
                        (gizonak + emakumeak) / 2;

                if (batezBestekoa > ehunekoGehiena) {
                    ehunekoGehiena = batezBestekoa;
                    herrialdeGehiena = herrialdea;
                }
            }

            double gizonenBatezBestekoa =
                    gizonenBatura / herrialdeKopurua;

            double emakumeenBatezBestekoa =
                    emakumeenBatura / herrialdeKopurua;

            System.out.println("===== POBREZIA DATUAK =====");
            System.out.println("Herrialde kopurua: "
                    + herrialdeKopurua);

            System.out.printf(
                    "Gizonen batez bestekoa: %.2f%%%n",
                    gizonenBatezBestekoa);

            System.out.printf(
                    "Emakumeen batez bestekoa: %.2f%%%n",
                    emakumeenBatezBestekoa);

            System.out.println(
                    "Ehunekorik handiena duen herrialdea:");

            System.out.printf(
                    "%s (%.2f%%)%n",
                    herrialdeGehiena,
                    ehunekoGehiena);

        } catch (IOException e) {

            System.out.println(
                    "Errorea fitxategia irakurtzean: "
                    + e.getMessage());

        } finally {

            try {

                if (irakurlea != null) {
                    irakurlea.close();
                }

                if (fis != null) {
                    fis.close();
                }

            } catch (IOException e) {

                System.out.println(
                        "Errorea fitxategia ixtean.");
            }
        }
    }
}
