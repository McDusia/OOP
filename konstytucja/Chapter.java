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
	
	public Article getArticle(int nr)
	{
		for(int i=0;i<sections.size();i++)
		{
			Article a=sections.get(i).getArticle(nr);
			if (a!=null)
				return a;
		}
		return null;
	}
	
	
	public void joinDividedWords()
	{
		// usuwa z ko�ca linii znaki przeniesienia do nowej liniii (-) i ��czy� s�owa w ca�o��,
		// Utworzy now� zawarto�� listy introduction
		// Nast�pnie zrobi to samo dla wszystkich rozdzia��w, sekcji i artyku��w.
	}
	public void deleteDates()
	{
	}
	public String toString()
	{
		String wynik = title+"\r";
		for(int i=0;i<sections.size();i++)
		{
			
			wynik += sections.get(i).toString()+"\r";
		}
		return wynik;
	}

}
