package sejmometr;

public interface ISejmometr {

	/*Opracuj system, który na podstawie argumentów linii poleceñ wyœwietla nastêpuj¹ce informacje (dla okreœlonej kadencji sejmu):
	-suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku
	-wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki
	-œredniej wartoœci sumy wydatków wszystkich pos³ów
	--pos³a/pos³anki, który wykona³ najwiêcej podró¿y zagranicznych
	pos³a/pos³anki, który najd³u¿ej przebywa³ za granic¹
	pos³a/pos³anki, który odby³ najdro¿sz¹ podró¿ zagraniczn¹
	listê wszystkich pos³ów, którzy odwiedzili W³ochy
	Program powinien obs³ugiwaæ b³êdy oraz zawieraæ testy weryfikuj¹ce poprawnoœæ jego dzia³ania.
*/
	//lista pos³ów   classes: DaputiesList, DeputyRecord, DeputyData
	//informacje o poszczególnych pos³ach   classes: Deputy, DeputyInfo
	
	//------------inne dane z serwisu, których nie wykorzystam w programie:
	//pobieranie listy posiedzeñ sejmu
	//pobieranie listy dni posiedzeñ sejmowych
	//pobieranie wyst¹pieñ sejmowych
	//pobieranie pe³nych treœci wyst¹pieñ sejmowych
	
	//other classes: ParliamentSystem, Loader, ChangeJsonToClasses	
	
	
	// funkcja zwraca sumê wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku
	double expensesSum (Deputy posel);
	double expensesForLittleRepairs (Deputy posel);
	double avgExpenses (DeputiesList poslowie);
	Deputy bestTraveler (DeputiesList poslowie);
	Deputy longestAbroad (DeputiesList poslowie);
	Deputy theMostExpensiveTravel (DeputiesList poslowie);
	Deputy[] visitItaly (DeputiesList poslowie);
		
	
}
