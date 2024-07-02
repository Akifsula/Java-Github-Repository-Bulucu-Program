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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DosyaOku {

	public List<String[]> javaDosyalariniOku(String klasorYolu) {
		List<String[]> javaDosyasiIcerikleri = new ArrayList<>();
		File klasor = new File(klasorYolu);

		File[] dosyalar = klasor.listFiles();
		if (dosyalar != null) {
			for (File dosya : dosyalar) {
				if (dosya.isDirectory()) {
					javaDosyasiIcerikleri.addAll(javaDosyalariniOku(dosya.getAbsolutePath()));
				} else if (dosya.getName().endsWith(".java")) {
					try {
						String dosyaIcerigi = new String(Files.readAllBytes(dosya.toPath()));
						if (sinifAdiniBul(dosyaIcerigi)) {
							javaDosyasiIcerikleri.add(new String[] { dosyaIcerigi, dosya.getName() });
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return javaDosyasiIcerikleri;
	}

	public boolean sinifAdiniBul(String dosyaIcerigi) {
		Pattern pattern = Pattern.compile("class\\s+(\\w+)");

		Matcher matcher = pattern.matcher(dosyaIcerigi);
		if (matcher.find()) {
			return true;
		}

		return false;
	}
}
