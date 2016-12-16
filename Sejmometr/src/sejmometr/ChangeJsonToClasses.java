package sejmometr;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChangeJsonToClasses {
	
	public void change(){
	
		try {
			Loader loaded = new Loader (new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8"));
					
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}