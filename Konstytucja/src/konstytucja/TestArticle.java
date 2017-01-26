package konstytucja;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestArticle {

	@Test
	public void test() {
		//test sprawdzaj�cy poprawno�� metody toString z klasy Article
		Article a = new Article(2,"Art. 2.");
		a.addRow("Cos testujacego");
		assertEquals(a.toString(),"Art. 2.\r" + "Cos testujacego" );
		Article b = new Article(2,"Art. 3.");
		b.addRow("cos nowego");
		assertEquals(b.toString(),"Art. 3.\r"+ "cos nowego");
	}

}
