package konstytucja;

import java.io.IOException;

public class ConstitutionSystem {
	
	
	public static void main (String[] args) throws IllegalArgument, IOException
	{
		try{
		ConstitutionParser parser = new ConstitutionParser();
		Constitution constitution = parser.load(args[0]);
		
			switch (args[1])
			{
				case "r":
					constitution.displayChapter(args[2]);
					break;
				
				case "a":
					constitution.displayArticle(args[2]);
					break;
				
			}
		}
		catch (IllegalArgument ex) {System.out.println(ex.getMessage());}
	}
	
}
	
