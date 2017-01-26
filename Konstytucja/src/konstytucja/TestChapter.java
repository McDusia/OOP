package konstytucja;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestChapter {

	@Test
	public void test() {
		//test sprawdzaj¹cy poprawnoœæ metody getArticle i toString z klasy Chapter
		Chapter c = new Chapter(2,"Rozdzial 2");
		Section s = new Section(1,"RZECZPOSPOLITA");
		s.addArticle(2,"Art. 2.");
		Article a =s.getArticle(2);
		a.addRow("cos");
		
		c.addSection(s);
		
		assertEquals(c.toString(),"Rozdzial 2\r"+"RZECZPOSPOLITA\r"+"Art. 2.\r" + "cos" + "\r");
	}

}
