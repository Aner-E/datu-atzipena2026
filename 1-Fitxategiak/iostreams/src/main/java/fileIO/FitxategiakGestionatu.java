package fileIO;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FitxategiakGestionatu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int aukera;
        do {
            System.out.println("\n===== MENUA =====");
            System.out.println("1. Egiaztatu fitxategia/direktorioa");
            System.out.println("2. Karpeta baten edukia bistaratu (Lehen maila)");
            System.out.println("3. Karpeta egitura sortu");
            System.out.println("4. Fitxategia sortu deskribapenarekin");
            System.out.println("0. Irten");
            System.out.print("Aukeratu: ");
            aukera = sc.nextInt();
            sc.nextLine(); // Garbitu introa

            switch (aukera) {
                case 1:
                    egiaztatuPath();
                    break;
                case 2:
                    erakutsiEdukia();
                    break;
                case 3:
                    sortuEgitura();
                    break;
                case 4:
                    sortuFitxategia();
                    break;
                case 0:
                    System.out.println("Programa itxi da.");
                    break;
                default:
                    System.out.println("Aukera okerra.");
            }
        } while (aukera != 0);
    }

    // Files.exists erabiliz existitzen den egiaztatu 
    public static void egiaztatuPath() {
        System.out.print("Sartu path absolutua: ");
        Path path = Path.of(sc.nextLine());

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                System.out.println("Existitzen da eta DIREKTORIOA da.");
            } else {
                System.out.println("Existitzen da eta FITXATEGIA da.");
            }
        } else {
            System.out.println("Ez da existitzen.");
        }
    }

    // DirectoryStream erabiliz edukia erakutsi
    public static void erakutsiEdukia() {
        System.out.print("Sartu karpetaren izena: ");
        Path path = Path.of(sc.nextLine());

        if (Files.isDirectory(path)) {
            System.out.println("\nEdukia:");
            // try-with-resources-ek ziurtatzen du stream-a automatikoki itxiko dela
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path elementua : stream) {
                    System.out.println(" - " + elementua.getFileName());
                }
            } catch (IOException e) {
                System.out.println("Errorea edukia irakurtzean.");
            }
        } else {
            System.out.println("Ez da karpeta bat edo ez da existitzen.");
        }
    }

    // Hainbat direktorio batera sortu
    public static void sortuEgitura() {
        try {
            // Files.createDirectories-ek beharrezko bide osoa sortzen du 
            Files.createDirectories(Path.of("karpeta_berriak/animaliak/arrainak"));
            Files.createDirectories(Path.of("karpeta_berriak/animaliak/ugaztunak"));
            Files.createDirectories(Path.of("karpeta_berriak/elikagaiak/barazkiak"));
            Files.createDirectories(Path.of("karpeta_berriak/elikagaiak/esnekiak"));
            System.out.println("Egitura sortu da!");
        } catch (IOException e) {
            System.out.println("Errorea karpetak sortzean.");
        }
    }

    //  Fitxategia sortu lehenik karpeta bilatuz 
    public static void sortuFitxategia() {
        System.out.print("Zer zoaz deskribatzera? (adib: ugaztunak): ");
        String kategoria = sc.nextLine();
        System.out.print("Zein? (adib: tigrea): ");
        String izena = sc.nextLine();
        System.out.print("Nolakoa da?: ");
        String deskribapena = sc.nextLine();

        // if bat erabiltzen dugu kategoria ikusteko eta beran karpetan gordetzeko
        Path kategoriaPath = null;
        if (kategoria.equals("ugaztunak") || kategoria.equals("arrainak")) {
            kategoriaPath = Path.of("karpeta_berriak/animaliak", kategoria);
        } else if (kategoria.equals("barazkiak") || kategoria.equals("esnekiak")) {
            kategoriaPath = Path.of("karpeta_berriak/elikagaiak", kategoria);
        }

        if (kategoriaPath != null && Files.exists(kategoriaPath)) {
            try {
                Path fitxategiBerria = kategoriaPath.resolve(izena + ".txt");
                // Testua modu zuzen eta sinplean idatzi
                Files.writeString(fitxategiBerria, deskribapena, StandardOpenOption.CREATE);
                System.out.println("Fitxategia sortu da: " + fitxategiBerria);
            } catch (IOException e) {
                System.out.println("Errorea fitxategia idaztean.");
            }
        } else {
            System.out.println("Kategoria hori ez daukagu gure egituran.");
        }
    }
}