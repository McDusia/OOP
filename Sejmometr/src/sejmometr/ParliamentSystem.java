package sejmometr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ParliamentSystem {

	public static void main (String[] args) {	

		/*Opracuj system, który na podstawie argumentów linii poleceñ wyœwietla nastêpuj¹ce informacje (dla okreœlonej kadencji sejmu):
			suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku
			wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki
			œredniej wartoœci sumy wydatków wszystkich pos³ów
			pos³a/pos³anki, który wykona³ najwiêcej podró¿y zagranicznych
			pos³a/pos³anki, który najd³u¿ej przebywa³ za granic¹
			pos³a/pos³anki, który odby³ najdro¿sz¹ podró¿ zagraniczn¹
			listê wszystkich pos³ów, którzy odwiedzili W³ochy
			Program powinien obs³ugiwaæ b³êdy oraz zawieraæ testy weryfikuj¹ce poprawnoœæ jego dzia³ania.
	*/
		//changeJsonToClasses jakasnazwa = new changeJsonToClasses().change;
		
		//args[0] <- imiê i nazwisko pos³a, którego informacje program bêdzie wylicza³
			ChangeJsonToClasses lista = new ChangeJsonToClasses();
			lista.change();
		
		
	}
}
