package sejmometr;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ChangeJsonToClasses {
	
	byte[] readedJson;
	List <Deputy> deputyList;
	
	public ChangeJsonToClasses()
	{
		deputyList = new ArrayList<>();
	}
	
	public void change(){
	
		try {
			
			Loader loaded = new Loader (new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8"));
			readedJson=loaded.data;
			
			DeputyInfo walker;
			/*"id": "197",
			"dataset": "poslowie",
			"url": "https:\/\/api-v3.mojepanstwo.pl\/dane\/poslowie\/197",
			"mp_url": "https:\/\/mojepanstwo.pl\/dane\/poslowie\/197",
			"schema_url": "https:\/\/api-v3.mojepanstwo.pl\/schemas\/dane\/poslowie.json",
			"global_id": "1556698",
			"slug": "kraczkowski-maks",
			8."score": null,
			9."data": {
			*/
			boolean pierwsza = false, druga = false, trzecia = false;
			for(int i=0;i<readedJson.length;i++)
			{
				boolean data = false;
				if(readedJson[i]=='{' && !pierwsza) pierwsza = true;
				if(readedJson[i]=='{' && pierwsza && !druga) druga = true;
				if(readedJson[i]=='{' && druga && !trzecia) 
				{
					trzecia = true;
					while(readedJson[i]!=':' && i<readedJson.length)
						i++;
					i++; //omitting first "
					String s ="";
					
					while(readedJson[i]!='"' && i< readedJson.length)
					{	char cos;
						cos = (char)readedJson[i];
						StringBuilder cos2 =new StringBuilder().append(cos); 
						String znak;
						znak= cos2.toString();
						s.concat(znak);
					}	

					Deputy readed = new Deputy();
					readed.addId(s);
					deputyList.add(new Deputy());
					while(readedJson[i]!=':' && i<readedJson.length)
						i++;
					i++; //omitting "
					while(readedJson[i]!='"' && i< readedJson.length)
					{	char cos;
						cos = (char)readedJson[i];
						StringBuilder cos2 =new StringBuilder().append(cos); 
						String znak;
						znak= cos2.toString();
						s.concat(znak);
					}	

					
					//{"ludzie.id":"186","ludzie.nazwa":"Maks Kraczkowski
				}
			}
				
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
}