import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DeputyList {

    private  List <Deputy> deputies;

    public DeputyList()
    {
        deputies = new ArrayList<>();
    }


    public void listMaker(String nrKadencji) {

        try {
            URL url;
            int pageQnt = 0;
            String adress;
            if(nrKadencji=="7") {
                url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
                adress = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions%5Bposlowie.kadencja%5D=7&_type=objects&page=";
                pageQnt = 11;
                }
            else {
                url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
                adress = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions%5Bposlowie.kadencja%5D=8&_type=objects&page=";
                pageQnt = 10;
            }
                    Loader loaded = new Loader(url);
                    ChangeJsonToClasses changer = new ChangeJsonToClasses();
                    changer.changeToDeputyList(loaded);
                    deputies=changer.returnDeputyList();

            for(int i = 2;i<=pageQnt;i++)
            {
                String pageNumber = String.valueOf(i);
                String adressTmp = adress;
                adress = adress.concat(pageNumber);
                url = new URL(adress);
                Loader loaded2 = new Loader(url);
                ChangeJsonToClasses changer2 = new ChangeJsonToClasses();
                changer2.changeToDeputyList(loaded2);
                addToList(changer2.returnDeputyList());
                adress = adressTmp;
            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTrips()
    {
        for(Deputy deputy : deputies) {
            deputy.loadTravels();
            deputy.calculateNumberOfTrips();
        }
    }

    public void addToList(List<Deputy> tmpList) {
        deputies.addAll(tmpList);
    }
    public Deputy returnIDifElement(String name, String surname)
    {
        for(int i =0;i<deputies.size();i++)
        {
            Deputy d = deputies.get(i);
            if(d.returnName().contains(name) && d.returnName().contains(surname))
                return deputies.get(i);
        }
        return null;
    }

    public double AverageExpenses()
    {
        for (Deputy deputy : deputies)
        {
            deputy.loadExpenses();
            deputy.calculateTotalExpenses();
        }
        BigDecimal total = new BigDecimal(0);
        BigDecimal divisor = new BigDecimal(0);
        BigDecimal one = new BigDecimal(1);
        for(int i=0;i<deputies.size();i++)
        {
            Deputy d = deputies.get(i);
            BigDecimal totalForDeputy = d.OneDeputyTotalExpenses();
            total = total.add(totalForDeputy);
            divisor = divisor.add(one);
        }
        double sum=total.doubleValue();
        double avg = sum/ divisor.intValue();
        return avg;
    }

    public void printWhoVisitedItaly()
    {
        for(Deputy deputy : deputies) {
            if(deputy.wasInItaly())
                System.out.println(deputy.returnName());
        }
    }

    public String bestTraveller()
    {
        int max=0;
        String name ="";
        for(Deputy element : deputies)
        {
            if(element.getLiczbaWyjazdow()>max)
            {
                max=element.getLiczbaWyjazdow();
                name=element.returnName();
            }
        }
        return name;
    }
    public String longestTraveller()
    {
        int max=0;
        String name ="";
        for(Deputy deputy : deputies)
        {
           int daysAbroad = deputy.daysAbroadCount();
            if(daysAbroad>max)
            {
                max=daysAbroad;
                name=deputy.returnName();
            }
        }
        return name;
    }

    public String withMostExpensiveTravel()
    {
        BigDecimal max= new BigDecimal(0);
        String name ="";
        for(Deputy deputy : deputies)
        {
            BigDecimal oneTravelExpense = deputy.maxTravelExpense();
            if(oneTravelExpense.compareTo(max)>0)
            {
                max=oneTravelExpense;
                name=deputy.returnName();
            }
        }
        return name;
    }

    public List<Deputy> returnDeputyList()
    {
        return deputies;
    }
}