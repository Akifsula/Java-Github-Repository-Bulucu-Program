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

import java.util.List;

public class Program {
    public static void main(String[] args) {

        DosyaOku dosyaOku = new DosyaOku();
        Fonksiyonlar fonks = new Fonksiyonlar();
        GitKlon gitKlon = new GitKlon();

        String klasorYolu = "./Repository";
        List<String[]> dosyaIcerikleri;

        boolean yukleme = gitKlon.Klonla(klasorYolu);

        
        if (yukleme) {
            dosyaIcerikleri = dosyaOku.javaDosyalariniOku(klasorYolu);
            int boyut = dosyaIcerikleri.size();
            int i = 0;
            System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
            for (String[] dosyaBilgileri : dosyaIcerikleri) {
                String dosyaIcerigi = dosyaBilgileri[0];
                String dosyaAdi = dosyaBilgileri[1];
                fonks.FonksiyonUygula(dosyaIcerigi, dosyaAdi);
                if (i < boyut - 1) {
                    System.out.println("-----------------------------------------");
                }
                i++;
            }
        } else {
            System.out.println("İşlem Başarısız...");
        }

    }
}
