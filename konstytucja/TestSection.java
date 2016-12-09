package konstytucja;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSection {

	@Test
	public void test() {
		Section s = new Section(2,"RZECZPOSPOLITA");
		//Article a = new Article(2,"Art. 2.");
		
		s.addArticle(2,"Art. 2.");
		Article a =s.getArticle(2);
		a.addRow("cos");
		assertEquals(s.toString(),"RZECZPOSPOLITA\r"+"Art. 2.\r" + "cos" );
	}

}
