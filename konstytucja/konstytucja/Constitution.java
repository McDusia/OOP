package konstytucja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Constitution implements IConstitution{
	
	List <Chapter> chapters;  
	List <String> introduction;	//lista linii tworz¹cych wstêp
	
	public Constitution()
	{
		chapters = new ArrayList<>();
		introduction = new ArrayList<>();
	}
	
	public static boolean isUpperCase(String s)
	{
		boolean upperCaseFound=false;
	    for (int i=0; i<s.length(); i++)
	    {
	    	if (Character.isUpperCase(s.charAt(i)))
	    		upperCaseFound = true;
	        if (Character.isLowerCase(s.charAt(i)))
	            return false;
	    }
	    if (upperCaseFound)
	    	return true;
	    return false;
	}
	

	
	public void load(String path)
	{
		Path file = Paths.get(path);
		Path file2 = Paths.get("C:\\Users\\Madzia\\konst\\wynik.txt");
		System.out.println(file.toString());
		try(OutputStream out = Files.newOutputStream(file2);BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8")))
		{		
			try (InputStream in = Files.newInputStream(file);BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
				String line = null;
				 int numChapter = 0, numSection = 0, numArticle = 0; //indeksy w liœcie podtypu dla danej klasy
				 int quanArticle = 0; //artyku³ów od pocz¹tku tekstu - dan¹ czêœæ tekstu tak numeruje u¿ytkownik
				while ((line = reader.readLine()) != null)
				{
								
					if(line.startsWith("Rozdzia³ "))
					{
						numChapter++;
						numSection = 0;
						numArticle = 0;
						chapters.add(new Chapter(numChapter,line));
				if(line.startsWith("Rozdzia³ II")) 
					System.out.println("blad");
					}
					else if (numChapter == 0)
					{
						introduction.add(line);
					}
					else if(isUpperCase(line) && line.length()>1)
					{
						numSection++;
						numArticle = 0;
						chapters.get(numChapter-1).addSection(new Section(numSection-1,line));
					}
					else if(line.startsWith("Art."))
					{
						numArticle++;
						quanArticle++;
						chapters.get(numChapter-1).sections.get(numSection-1).addArticle(quanArticle, line);
					}
					else
					{
						chapters.get(numChapter-1).sections.get(numSection-1).articles.get(numArticle-1).addRow(line);
					}
					
					System.out.println(line);
					writer.write (line);
				}				
			} catch (IOException x) {
			    System.err.println(x);			    
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	Article GetArticle(int nr)
	{
		for(int i=0; i<chapters.size(); i++)
		{
			Article a =chapters.get(i).GetArticle(nr);
			if (a!=null)
				return a;
		}
		return null;
	}
	
	Chapter GetChapter(int nr)
	{
		for (int i=0; i<chapters.size(); i++)
		{
			if (chapters.get(i).number==nr)
				return chapters.get(i);
		}
		return null;
	}
	
	public void DisplayArticle(String articles)
	{
		// Need to parse articles string articles may be like "5-10" or "44"
		int start=5;
		int end=10;
		for (int i=start;i<=end;i++)
		{
			Article a = GetArticle(i);
			String s=a.toString();
			System.out.print(s);
		}
	}

	public void DisplayChapter(String chapterNr)
	{
		int nr=5; // Need to parse chapterNr string.  Chapter may be like "7"
		Chapter c = GetChapter(nr);
		String s=c.toString();
		System.out.print(s);
		
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