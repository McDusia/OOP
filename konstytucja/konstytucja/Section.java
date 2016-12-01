package konstytucja;
import java.util.ArrayList;
import java.util.List;

public class Section implements IConstitution{

	List <Article> articles;
	int numSection;
	String title;
	
	public Section (int a, String t)
	{
		this.numSection = a;	//mo�na nie pisa� this, pola s� dost�pne w ca�ej klasie
		this.title = t;
		this.articles = new ArrayList<>();
	}
	
	public void addArticle(int a, String t)
	{
		articles.add(new Article(a,t));
	}
	
	public Article GetArticle(int nr)
	{
		for(int i=0;i<articles.size();i++)
		{
			if (articles.get(i).number==nr)
				return articles.get(i);
		}
		return null;
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