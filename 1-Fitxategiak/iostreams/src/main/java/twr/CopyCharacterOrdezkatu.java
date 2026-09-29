package twr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyCharacterOrdezkatu {
    public static void main(String[] args) throws IOException {

        try (FileInputStream in = new FileInputStream("xanadu.txt");
                FileOutputStream out = new FileOutputStream("outagain.txt")) {

            int caracter;

            while ((caracter = in.read()) != -1) {

                if (caracter == 'a') {
                    caracter = 'o';
                }

                out.write(caracter);
            }

        } catch (FileNotFoundException e) {
            System.out.println(
                    "Errorea: kopiatu beharreko fitxategia ez da aurkitu.");

        }
    }
}
