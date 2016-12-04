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

public class ConstitutionParser {

	public Constitution load(String path)
	{
		Constitution konst = new Constitution();
		Path file = Paths.get(path);
		Path file2 = Paths.get("C:\\Users\\Madzia\\konst\\wynik.txt");
		//System.out.println(file.toString());
		try(OutputStream out = Files.newOutputStream(file2);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,"UTF-8")))
		{		
			try (InputStream in = Files.newInputStream(file);
					BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
				String line = null;
				String temp = null;
				 int numChapter = 0, numSection = 0, numArticle = 0; //indeksy w liœcie podtypu dla danej klasy
				 int quanArticle = 0; //artyku³ów od pocz¹tku tekstu - dan¹ czêœæ tekstu tak numeruje u¿ytkownik
				while ((line = reader.readLine()) != null)
				{
					if(temp!=null)
					{
						line = temp+line;
						temp = null;
					}
					if(line.endsWith("Kancelaria Sejmu"))
					{
						line = reader.readLine();
					}
					if(line.equals("2009-11-16"))
					{
						line = reader.readLine();
					}
					if(line.endsWith("-"))
					{	
						line=line.substring(0, line.length()-1);
						int lastWord = line.lastIndexOf(" ");
						temp = line.substring(lastWord+1);
						line =line.substring(0, lastWord);
						
					}
					if(line.startsWith("Rozdzia³ "))
					{
						numChapter++;
						numSection = 0;
						numArticle = 0;
						konst.chapters.add(new Chapter(numChapter,line));
			
					}
					else if (numChapter == 0)
					{
						konst.introduction.add(line);
					}
					else if(isUpperCase(line) && line.length()>1)
					{
						numSection++;
						numArticle = 0;
						konst.chapters.get(numChapter-1).addSection(new Section(numSection-1,line));
					}
					else if(line.startsWith("Art."))
					{
						numArticle++;
						quanArticle++;
						konst.chapters.get(numChapter-1).sections.get(numSection-1).addArticle(quanArticle, line);
					}
					else
					{
						konst.chapters.get(numChapter-1).sections.get(numSection-1).articles.get(numArticle-1).addRow(line);
					}
					
					//System.out.println(line);
					writer.write (line);
				}				
			} catch (IOException x) {
			    System.err.println(x);			    
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return konst;
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
	public void joinDividedWords()
	{
		
		
		// usuwa z koñca linii znaki przeniesienia do nowej liniii (-) i ³¹czy³ s³owa w ca³oœæ,
		// Utworzy now¹ zawartoœæ listy introduction
		// Nastêpnie zrobi to samo dla wszystkich rozdzia³ów, sekcji i artyku³ów.
	}
	
	public void deleteDates()
	{
		
		
	}
	
}
