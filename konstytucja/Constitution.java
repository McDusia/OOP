package konstytucja;

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
	
	Article getArticle(int nr)
	{
		for(int i=0; i<chapters.size(); i++)
		{
			Article a =chapters.get(i).getArticle(nr);
			if (a!=null)
				return a;
		}
		return null;
	}
	
	Chapter getChapter(int nr)
	{
		for (int i=0; i<chapters.size(); i++)
		{
			if (chapters.get(i).number==nr)
				return chapters.get(i);
		}
		return null;
	}
	
	public void displayArticle(String articles) throws IllegalArgument
	{
		// Need to parse articles string articles may be like "5-10" or "44"
		try{
		IllegalArgument ex = new IllegalArgument("niepoprawny argument");
		if(articles.contains("-"))
		{
			String startString[] = articles.split("-");
			int start = Integer.valueOf(startString[0]);
			int end = Integer.valueOf(startString[1]);
			
			for (int i=start;i<=end;i++)
			{
				if(i<1 || i>243) throw ex; 
				else{
					Article a = getArticle(i);
					String s=a.toString();
					System.out.print(s+"\r");
				}
			}
		}
		else
		{
			int onlyOne = Integer.valueOf(articles);
			if(onlyOne<1 || onlyOne>243) throw ex;
			else{
			Article a = getArticle(onlyOne);
			String s=a.toString();
			System.out.print(s+"\r");
			}
		}
		} catch (NumberFormatException e){System.out.println("ten argument nie jest liczba");}
		
	}

	public void displayChapter(String chapterNr) throws IllegalArgument
	{
		//13 chapters
		try{
			IllegalArgument ex = new IllegalArgument("niepoprawny argument");
		if(chapterNr.contains("-"))
		{
			String startString[] = chapterNr.split("-");
			if(startString.length!=3 && startString.length!=4) throw new IllegalArgument ("z³y format danych wejœciowych");
			int start = Integer.valueOf(startString[0]);
			int end = Integer.valueOf(startString[1]);
			for (int i=start;i<=end;i++)
			{
				if(i<1 || i>13) throw ex; 
				else{
					Chapter c = getChapter(i);
					String s = c.toString();
					System.out.print(s);
				}
			}
		}
		
		else 
		{	int nr = Integer.valueOf(chapterNr); //type conversion String -> Integer
			
			if(nr>=1 && nr<=13)
			{
				Chapter c = getChapter(nr);
				String s = c.toString();
				System.out.print(s);
			}
			else System.out.print("W dokumencie \"Konstytucja Rzeczpospolitej Polskiej\" jest 13 rozdzia³ów. \r");
			
		}
		} catch (NumberFormatException e){System.out.println("ten argument nie jest liczb¹");}
	}
	
}