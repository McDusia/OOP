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
