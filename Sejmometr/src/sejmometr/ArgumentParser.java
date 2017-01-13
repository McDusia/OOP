import java.net.URL;

/**
 * Created by Madzia on 2017-01-12.
 */
public class ArgumentParser {

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