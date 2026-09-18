package paagbi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyCharacterUpper {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");

            int caracter;

            while ((caracter = in.read()) != -1) {

                caracter = Character.toUpperCase((char) caracter);

                out.write(caracter);
            }

        } catch (FileNotFoundException e) {
            System.out.println(
                "Errorea: kopiatu beharreko fitxategia ez da aurkitu."
            );

        } finally {
            if (in != null) {
                in.close();
            }

            if (out != null) {
                out.close();
            }
        }
    }
}

