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
		// usuwa z ko�ca linii znaki przeniesienia do nowej liniii (-) i ��czy� s�owa w ca�o��,
		// Utworzy now� zawarto�� listy rows.
	}

	public void DeleteDates()
	{
	}

}
