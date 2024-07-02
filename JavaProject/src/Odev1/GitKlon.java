/**
*
* @author Akif Emre Sula - akif.sula@ogr.sakarya.edu.tr
* @since 05.04.2024
* <p>
* 	Sakarya Üniversitesi 
* 	Bilgisayar Mühendisliği
* 	Programa Dillerinin Prensipleri  1C Grubu 1. Ödev 
* </p>
*/
package Odev1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GitKlon {

    public boolean Klonla(String klonlamaDizini) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Git linkini giriniz: ");
        String gitLinki = scanner.nextLine();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("git", "clone", gitLinki);
            processBuilder.directory(new File(klonlamaDizini));
            Process islem = processBuilder.start();

            int exitCode = islem.waitFor();
            if (exitCode == 0) {
                System.out.println("Git klonlama işlemi başarıyla tamamlandı.");
            } else {
                System.out.println("Git klonlama işlemi başarısız oldu. Çıkış kodu: " + exitCode);
            }
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            scanner.close();
        }
    }
}
