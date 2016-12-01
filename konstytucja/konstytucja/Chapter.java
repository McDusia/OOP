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
		// usuwa z ko�ca linii znaki przeniesienia do nowej liniii (-) i ��czy� s�owa w ca�o��,
		// Utworzy now� zawarto�� listy introduction
		// Nast�pnie zrobi to samo dla wszystkich rozdzia��w, sekcji i artyku��w.
	}
	public void DeleteDates()
	{
	}

}
