package konstytucja;


public class ConstitutionParser {
	
	
	
	public static void main (String[] args)
	{
		Constitution constitution = new Constitution();
		constitution.load(args[0]);
		switch (args[1])
		{
			case "r":
				constitution.DisplayChapter(args[2]);
				break;
			case "a":
				constitution.DisplayArticle(args[2]);
				break;
			case "-":
				constitution.JoinDividedWords();
				break;
			case "d":
				constitution.DeleteDates();
				break;
		}

		
		return;
			
	}
	
	
}
	
