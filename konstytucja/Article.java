package konstytucja;
import java.util.ArrayList;
import java.util.List;

public class Article {

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
}
