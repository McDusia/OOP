import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Madzia on 2017-01-13.
 */
public class DeputyTest {
    @Test
    public void addName() throws Exception {
    Deputy d = new Deputy();
    d.addName("Stanisław Badura");
        assertEquals("Stanisław Badura",d.returnName());
    }
    @Test
    public void addId() throws Exception {
        Deputy d = new Deputy();
        d.addId("123");
        assertEquals("123",d.returnID());
    }

    @Test
    public void OneDeputyTotalExpenses () throws Exception {
        Deputy deputy = new Deputy();
        deputy.addId("174");
        deputy.addName("Sławomir Kłosowski");
        deputy.loadExpenses();
        deputy.calculateTotalExpenses();
        assertEquals(deputy.getLittleRepairs().toString(),"8418.42");
        assertEquals(deputy.OneDeputyTotalExpenses().toString(),"277061.64");
        Deputy deputy2 = new Deputy();
        deputy2.addId("186");
        deputy2.addName("Maks Kraczkowski");
        deputy2.loadExpenses();
        deputy2.calculateTotalExpenses();
    }

}