package konstytucja;
import java.util.ArrayList;
import java.util.List;

public class Article implements IConstitution {

	int number;
	String title;
	List <String> rows;
	
	public Article(int a, String t)
	{
		this.number = a;
		this.title = t;
		this.rows= new ArrayList<> ();
	}
	
	public void addRow (String row)
	{
		rows.add(row);
	}
	
	public void joinDividedWords()
	{
		// usuwa z koñca linii znaki przeniesienia do nowej liniii (-) i ³¹czy³ s³owa w ca³oœæ,
		// Utworzy now¹ zawartoœæ listy rows.
	}

	public void deleteDates()
	{
	}
	
	public String toString()
	{
		String wynik = title+"\r";
		int i;
		for(i=0;i<rows.size()-1;i++)
		{
			wynik += rows.get(i).toString()+"\r";
		}
		return wynik+rows.get(i).toString();
	}
}	
