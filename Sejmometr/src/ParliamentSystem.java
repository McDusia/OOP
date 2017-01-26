import java.io.IOException;

public class ParliamentSystem {

    public static void main(String[] args) throws IllegalArgument, IOException {

		/*Opracuj system, który na podstawie argumentów linii poleceń wyświetla następujące informacje (dla określonej kadencji sejmu):
			+suma wydatków posła/posłanki o określonym imieniu i nazwisku
			-wysokości wydatków na 'drobne naprawy i remonty biura poselskiego' określonego posła/posłanki
			+średniej wartości sumy wydatków wszystkich posłów
			+posła/posłanki, który wykonał najwięcej podróży zagranicznych
			+posła/posłanki, który najdłużej przebywał za granicą
			+posła/posłanki, który odbył najdroższą podróż zagraniczną
			+listę wszystkich posłów, którzy odwiedzili Włochy
			Program powinien obsługiwać błędy oraz zawierać testy weryfikujące poprawność jego działania.
	*/
        ArgumentParser p = new ArgumentParser();
        try {
            if (args.length == 0)
            {
                p.PrintInfo();
                return;
            }
            p.goodArgs(args);
            DeputyList deputies = new DeputyList();
            deputies.listMaker(args[0]);
            System.out.println(args[1]);
            switch(args[1]){
                case "s":
                case "n":
                    Deputy dep = deputies.returnIDifElement(args[2], args[3]);
                    if (dep == null)
                        throw new IllegalArgument("Nie odnaleziono posla o podanym imieniu i nazwisku, sprawdz czy poprawnie wprowadziles dane");
                    dep.loadExpenses();
                    dep.calculateTotalExpenses();
                    if(args[1].equals("s"))
                        System.out.println("Suma wydatków posła "+args[2]+" "+args[3]+ ": "+ dep.OneDeputyTotalExpenses().toString());
                    else
                        System.out.println("Wysokość wydatków na drobne naprawy i remonty biura poselskiego posła "+args[2]+" "+args[3]+ ": "+ dep.getLittleRepairs().toString());
                    break;
                case "a":
                    System.out.println("Srednie wydatki: " + deputies.AverageExpenses());
                    break;
                case "z":
                    deputies.loadTrips();
                    System.out.println("Najwięcej podróży zagranicznych wykonał(a) : " + deputies.bestTraveller());
                    break;
                case "d":
                    deputies.loadTrips();
                    System.out.println("Najdłużej za granicą przebywał(a) : " + deputies.longestTraveller());
                    break;
                case "e":
                    deputies.loadTrips();
                    System.out.println("Najdroższą podróż zagraniczną odbył(a) : " + deputies.withMostExpensiveTravel());
                    break;
                case "w":
                    deputies.loadTrips();
                    System.out.println("Posłowie, którzy odwiedzili Włochy: \n");
                    deputies.printWhoVisitedItaly();
                    break;
            }
        } catch (IllegalArgument ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}