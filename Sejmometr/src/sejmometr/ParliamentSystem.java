import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class ParliamentSystem {

    public static void main (String[] args) throws IllegalArgument{

		/*Opracuj system, który na podstawie argumentów linii poleceń wyświetla następujące informacje (dla określonej kadencji sejmu):
			suma wydatków posła/posłanki o określonym imieniu i nazwisku
			wysokości wydatków na 'drobne naprawy i remonty biura poselskiego' określonego posła/posłanki
			średniej wartości sumy wydatków wszystkich posłów
			posła/posłanki, który wykonał najwięcej podróży zagranicznych
			posła/posłanki, który najdłużej przebywał za granicą
			posła/posłanki, który odbył najdroższą podróż zagraniczną
			listę wszystkich posłów, którzy odwiedzili Włochy
			Program powinien obsługiwać błędy oraz zawierać testy weryfikujące poprawność jego działania.
	*/
        //ChangeJsonToClasses jakasnazwa = new ChangeJsonToClasses();
        //jakasnazwa.change();
        try {
            if (args.length == 0) throw new IllegalArgument("nie podano argumentow");
            DeputyListMaker a = new DeputyListMaker();
            a.listMaker("8");
            //a.listMaker("8");
            //a.listMaker(args[0]); <- docelowy sposob wywolania
            if (args.length == 1 || args.length == 2) throw new IllegalArgument("podano za malo argumentow");
            String id = a.returnIDifElement(args[1], args[2]);
            if (id == "")
                throw new IllegalArgument("Nie odnaleziono posla o podanym imieniu i nazwisku, sprawdz czy poprawnie wprowadziles dane");
            Expenses e = new Expenses(id);
            BigDecimal d = e.returnOneDeputyTotalExpenses();
            String s = d.toString();
            System.out.println(s);
            //AverageExpenses average = new AverageExpenses(a.returnDeputyList());
            Trips t = new Trips("12");

        }catch (IllegalArgument ex){
            System.out.println(ex.getMessage());
        }
    }
}