package konstytucja;
import java.util.ArrayList;
import java.util.List;

public class Section {

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
}