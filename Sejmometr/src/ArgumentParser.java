import java.net.URL;

/**
 * Created by Madzia on 2017-01-12.
 */
public class ArgumentParser {

    public void PrintInfo()
    {
        String info="Użycie programu:\n";
        info += "Test2 NrKadencji {s | n | a | z| d | e | w} [Imię Nazwisko]\n";
        info += "NrKadencji - Oznacza numer kadencji. Moży być 7 lub 8\n";
        info += "s - Wyświetla sumę wydatków posła/posłanki. Podać imię i nazwisko posłą/posłanki oddzielone spacją.\n";
        info += "n - Wyświetla wysokość wydatków na 'drobne naprawy i remonty biura poselskiego' posła/posłanki. Podać imię i nazwisko oddzielone spacją.\n";
        info += "a - Wyświetla średnią wartości sumy wydatków wszystkich posłów.\n";
        info += "z - Wyświetla posła/posłankę, który wykonał najwięcej podróży zagranicznych.\n";
        info += "d - Wyświetla posła/posłankę, który najdłużej przebywał za granicą.\n";
        info += "e - Wyświetla posła/posłankę, który odbył najdroższą podróż zagraniczną\n";
        info += "w - Wyświetla listę wszystkich posłów, którzy odwiedzili Włochy.\n";
        info += "Przykłdy użycia:\n";
        info += "Test2 7 s Jan Bury\n";
        info += "Test2 8 n Jan Bury\n";
        info += "Test2 7 a\n";
        info += "Test2 8 z\n";
        info += "Test2 7 d\n";
        info += "Test2 8 e\n";
        info += "Test2 7 w\n";
        System.out.println(info);
    }

    public void goodArgs(String[] args) throws IllegalArgument {
        if (args.length== 0) throw new IllegalArgument("Nie podano argumentow.");
        if (args.length == 1) throw new IllegalArgument("Podano za malo argumentow.");
        switch(args[0]) {
            case "7":
            case "8":
                break;
            default:
                throw new IllegalArgument("Niepoprawny numer kadencji.");
        }
        switch(args[1]){
            case "s":
            case "n":
                if(args.length!=4) throw new IllegalArgument("Zła liczba argumentów dla wybranego trybu.");
                break;
            case "a":
            case "z":
            case "d":
            case "e":
            case "w":
                if(args.length!=2) throw new IllegalArgument("Zła liczba argumentów dla wybranego trybu.");
                break;
            default:
                throw new IllegalArgument("Niepoprawny argument trybu.");
        }
    }
}