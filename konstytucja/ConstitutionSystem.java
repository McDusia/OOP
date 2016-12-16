package konstytucja;

import java.io.IOException;

public class ConstitutionSystem {
	
	
	public static void main (String[] args) throws IllegalArgument, IOException
	{
		
		try{
		ConstitutionParser parser = new ConstitutionParser();
		if(args.length==0) throw new IllegalArgument ("brak argumentów");
		if(args.length==1 || args.length==2) throw new IllegalArgument ("za ma³o argumentów");
		Constitution constitution = parser.load(args[0]);
		
			switch (args[1])
			{
				case "r":
					
					constitution.displayChapter(args[2]);
					break;
				
				case "a":
					constitution.displayArticle(args[2]);
					break;
				default:
					throw new IllegalArgument ("3 argument jest niepoprawny, wpisz poprawn¹ nazwê");
			}
		}
		catch (IllegalArgument ex) {System.out.println(ex.getMessage());}
	}
	
}
	
