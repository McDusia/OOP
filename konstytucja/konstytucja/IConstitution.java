package konstytucja;

public interface IConstitution {

	
	//Article GetArticle(int nr);
	//zwraca artyku� o podanym numerze, numerowanie jest zgodne z wy�wietlanym
	
	
	public void JoinDividedWords();
	//��czy s�owa porozdzielane znakiem "-" z ko�ca lini 
	
	public void DeleteDates();	
	// znajduje "Kancelaria Sejmu" oraz dat� i usuwa je z tre�ci
	public String toString();
	//generuje string
}
