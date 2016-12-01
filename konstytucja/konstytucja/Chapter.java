package konstytucja;
import java.util.ArrayList;
import java.util.List;

public class Chapter implements IConstitution {

int number;
String title;
List <Section> sections;

	public Chapter(int a, String t)
	{
		this.number = a;
		this.title = t;
		this.sections = new ArrayList<>();
	}
	
	public void addSection(Section s)
	{
		sections.add(s);
	}
	
	public Article GetArticle(int nr)
	{
		for(int i=0;i<sections.size();i++)
		{
			Article a=sections.get(i).GetArticle(nr);
			if (a!=null)
				return a;
		}
		return null;
	}
	
	public String toString()
	{
		return "";
	}
	public void JoinDividedWords()
	{
		// usuwa z koñca linii znaki przeniesienia do nowej liniii (-) i ³¹czy³ s³owa w ca³oœæ,
		// Utworzy now¹ zawartoœæ listy introduction
		// Nastêpnie zrobi to samo dla wszystkich rozdzia³ów, sekcji i artyku³ów.
	}
	public void DeleteDates()
	{
	}

}
