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


public class Constitution {
	public static void main (String[] args)
	{
		Path file = Paths.get(args[0]);
		Path file2 = Paths.get("C:\\Users\\Madzia\\konst\\wynik.txt");
		System.out.println(file.toString());

		List <Article> artykuly = new ArrayList<>();
		List <Chapter> chapters = new ArrayList<>();  
		
		
		try(OutputStream out = Files.newOutputStream(file2);BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out)))
		{		
			try (InputStream in = Files.newInputStream(file);BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			    String line = null;
			    
			    int numChapter = 0, numSection = 0, numArticle = 0;
			  
			    while ((line = reader.readLine()) != null) {			        
			    	if (line.startsWith("Rozdzia³ "))
			    	{
			    		numChapter++;
			    		chapters.add(new Chapter(numChapter,line));
			    	}
			    	else if(line.matches("[A-Z ]"))
			    	{
			    		numSection++;
			    		chapters.get(numChapter).addSection(new Section(numSection,line));
			    	}
			    	else if(line.startsWith("Art."))
			    	{
			    		numArticle++;
			    		chapters.get(numChapter).sections.get(numSection).addArticle(numArticle, line);
			    	}
			    	else if(numChapter == 0)
			    	{
			    		
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
}
