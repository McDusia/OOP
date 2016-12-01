package konstytucja;
import java.util.ArrayList;
import java.util.List;

public class Section implements IConstitution{

	List <Article> articles;
	int numSection;
	String title;
	
	public Section (int a, String t)
	{
		this.numSection = a;	//mo¿na nie pisaæ this, pola s¹ dostêpne w ca³ej klasie
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
		// usuwa z koñca linii znaki przeniesienia do nowej liniii (-) i ³¹czy³ s³owa w ca³oœæ,
		// Utworzy now¹ zawartoœæ listy introduction
		// Nastêpnie zrobi to samo dla wszystkich rozdzia³ów, sekcji i artyku³ów.
	}
	
	public void DeleteDates()
	{
	}

}