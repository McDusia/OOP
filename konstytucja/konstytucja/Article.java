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
	
	public String toString()
	{
		return "";
	}
	
	public void JoinDividedWords()
	{
		// usuwa z koñca linii znaki przeniesienia do nowej liniii (-) i ³¹czy³ s³owa w ca³oœæ,
		// Utworzy now¹ zawartoœæ listy rows.
	}

	public void DeleteDates()
	{
	}

}
