package konstytucja;


public class ConstitutionSystem {
	
	
	
	public static void main (String[] args)
	{
		ConstitutionParser parser = new ConstitutionParser();
		Constitution constitution = parser.load(args[0]);
		
		//constitution.joinDividedWords();
		//constitution.deleteDates();
		
		
		switch (args[1])
		{
			case "r":
				constitution.displayChapter(args[2]);
				break;
			case "a":
				constitution.displayArticle(args[2]);
				break;
		}

		return;
			
	}
	
	
}
	
