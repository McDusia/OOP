package sejmometr;

public interface ISejmometr {

	/*Opracuj system, kt�ry na podstawie argument�w linii polece� wy�wietla nast�puj�ce informacje (dla okre�lonej kadencji sejmu):
	-suma wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku
	-wysoko�ci wydatk�w na 'drobne naprawy i remonty biura poselskiego' okre�lonego pos�a/pos�anki
	-�redniej warto�ci sumy wydatk�w wszystkich pos��w
	--pos�a/pos�anki, kt�ry wykona� najwi�cej podr�y zagranicznych
	pos�a/pos�anki, kt�ry najd�u�ej przebywa� za granic�
	pos�a/pos�anki, kt�ry odby� najdro�sz� podr� zagraniczn�
	list� wszystkich pos��w, kt�rzy odwiedzili W�ochy
	Program powinien obs�ugiwa� b��dy oraz zawiera� testy weryfikuj�ce poprawno�� jego dzia�ania.
*/
	//lista pos��w   classes: DaputiesList, DeputyRecord, DeputyData
	//informacje o poszczeg�lnych pos�ach   classes: Deputy, DeputyInfo
	
	//------------inne dane z serwisu, kt�rych nie wykorzystam w programie:
	//pobieranie listy posiedze� sejmu
	//pobieranie listy dni posiedze� sejmowych
	//pobieranie wyst�pie� sejmowych
	//pobieranie pe�nych tre�ci wyst�pie� sejmowych
	
	//other classes: ParliamentSystem, Loader, ChangeJsonToClasses	
	
	
	// funkcja zwraca sum� wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku
	double expensesSum (Deputy posel);
	double expensesForLittleRepairs (Deputy posel);
	double avgExpenses (DeputiesList poslowie);
	Deputy bestTraveler (DeputiesList poslowie);
	Deputy longestAbroad (DeputiesList poslowie);
	Deputy theMostExpensiveTravel (DeputiesList poslowie);
	Deputy[] visitItaly (DeputiesList poslowie);
		
	
}
