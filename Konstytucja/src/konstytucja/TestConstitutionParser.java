package konstytucja;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestConstitutionParser {

	@Test
	
	public void test() {
		
		//chapters = new ArrayList<>();
		//introduction = new ArrayList<>();
		//test sprawdzaj¹cy poprawnoœæ metod z klasy ConstitutionParser
		Chapter c = new Chapter(2,"Rozdzial 2");
		Section s = new Section(1,"RZECZPOSPOLITA");
		s.addArticle(2,"Art. 2.");
		Article a =s.getArticle(2);
		a.addRow("cos");
		
		Constitution konst = new Constitution();
		
		konst.chapters.add(c);
		ConstitutionParser konstPars = new ConstitutionParser();
		assertTrue(konstPars.isUpperCase("TYYUT"));
		assertFalse(konstPars.isUpperCase("Tsdjh"));
		assertFalse(konstPars.isUpperCase("dsjfg"));
		assertEquals(konstPars.load("C:\\Users\\Madzia\\konst\\konstT.txt"),konst);
		
	}

}
