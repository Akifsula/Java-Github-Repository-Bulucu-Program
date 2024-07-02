/**
*
* @author Akif Emre Sula - akif.sula@ogr.sakarya.edu.tr
* @since 04.04.2024
* <p>
* 	Sakarya Üniversitesi 
* 	Bilgisayar Mühendisliği
* 	Programa Dillerinin Prensipleri  1C Grubu 1. Ödev 
* </p>
*/
package Odev1;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fonksiyonlar {

	public void FonksiyonUygula(String icerik, String isim) {
		int javadoc = javaDocHesapla(icerik);
		int yorum = yorumHesapla(icerik);
		int kod = kodSatirHesapla(icerik);
		int LOC = LOCHesapla(icerik);
		int fonksiyon = fonkHesapla(icerik);

		double sapma = sapmaHesapla(javadoc, yorum, fonksiyon, kod);
		DecimalFormat df = new DecimalFormat("0.00");
		String sapmaDuzenli = df.format(sapma).replace(",", ".");

		System.out.println("Sınıf: " + isim);
		System.out.println("Javadoc Satır Sayısı: " + javadoc);
		System.out.println("Yorum Satır Sayısı: " + yorum);
		System.out.println("Kod Satır Sayısı: " + kod);
		System.out.println("LOC: " + LOC);
		System.out.println("Fonksiyon Sayısı: " + fonksiyon);
		System.out.println("Yorum Sapma Yüzdesi: % " + sapmaDuzenli);
	}

	public int javaDocHesapla(String icerik) {
		int javadocSayisi = 0;
		boolean javaDocIcinde = false;

		String[] satirlar = icerik.split("\\r?\\n");
		for (String satir : satirlar) {
			satir = satir.trim();
			if (satir.startsWith("/**")) {
				javaDocIcinde = true;
				if (satir.endsWith("*/")) {
					javaDocIcinde = false;
					javadocSayisi++;
				}
			} else if (satir.startsWith("*/")) {
				javaDocIcinde = false;
			} else if (satir.startsWith("*") && javaDocIcinde) {
				javadocSayisi++;
			}
		}

		return javadocSayisi;
	}

	public int yorumHesapla(String icerik) {
		int yorumSayisi = 0;
		boolean yorumIcinde = false;

		String[] satirlar = icerik.split("\\r?\\n");

		for (String satir : satirlar) {
			satir = satir.trim();
			if (satir.contains("/*")) {
				if (!satir.contains("/**")) {
					yorumIcinde = true;
					if (satir.contains("*/")) {
						yorumIcinde = false;
						yorumSayisi++;
					}
				}

			} else if (satir.contains("//") && !yorumIcinde) {
				yorumSayisi++;
			} else if (yorumIcinde) {
				yorumSayisi++;
				if (satir.contains("*/")) {
					yorumIcinde = false;
					yorumSayisi--;
				}
			}
		}

		return yorumSayisi;
	}

	public int kodSatirHesapla(String icerik) {
		int kodSatirSayisi = 0;
		boolean yorumIcinde = false;

		String[] satirlar = icerik.split("\\r?\\n");

		for (String satir : satirlar) {
			satir = satir.trim();
			if (!satir.isEmpty() && !satir.startsWith("//")) {

				if (satir.contains("/*")) {
					yorumIcinde = true;
				}
				if (!yorumIcinde) {
					kodSatirSayisi++;
				}

				if (yorumIcinde && satir.contains("*/")) {
					yorumIcinde = false;
				}

			}
		}

		return kodSatirSayisi;
	}

	public int fonkHesapla(String icerik) {
		Pattern desen = Pattern.compile(
				"(?m)^\\s*(?:public|private|protected|static|final|abstract|synchronized|native|strictfp)\\b.*?\\w+\\s*\\(.*?\\)\\s*\\{");
		Matcher eslestirici = desen.matcher(icerik);

		int fonksiyonSayisi = 0;
		while (eslestirici.find()) {
			fonksiyonSayisi++;
		}

		return fonksiyonSayisi;
	}

	public int LOCHesapla(String icerik) {
		int kodSatirSayisi = 0;

		String[] satirlar = icerik.split("\\r?\\n");

		for (String satir : satirlar) {
			satir = satir.trim();
			kodSatirSayisi++;
		}

		return kodSatirSayisi;
	}

	public double sapmaHesapla(int javaDoc, int yorum, int fonksiyon, int kodSatir) {
		double YG = ((javaDoc + yorum) * 0.8) / fonksiyon;
		double YH = ((double) kodSatir / fonksiyon) * 0.3;

		double yorumSapmaYuzdesi = ((100 * YG) / YH) - 100;

		return yorumSapmaYuzdesi;
	}
}
