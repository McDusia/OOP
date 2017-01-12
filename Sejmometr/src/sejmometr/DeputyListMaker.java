
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DeputyListMaker {

    private List <Deputy> deputies;

    public DeputyListMaker()
    {
        deputies = new ArrayList<>();
    }


    public void listMaker(String nrKadencji) throws IllegalArgument{

        try {
            URL url;
            int pageQnt;
            String adress;
            switch(nrKadencji) {
                case "7": {
                    url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7");
                    adress = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions%5Bposlowie.kadencja%5D=7&_type=objects&page=";

                    pageQnt = 11;
                    break;
                }
                case "8": {
                    url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
                    adress = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions%5Bposlowie.kadencja%5D=8&_type=objects&page=";
                    pageQnt = 10;
                    break;
                }
                default:
                    throw new IllegalArgument("argument jest niepoprawny");

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
    public void addToList(List<Deputy> tmpList) {
        deputies.addAll(tmpList);
    }
    public String returnIDifElement(String name, String surname)
    {
        for(int i =0;i<deputies.size();i++)
        {
            Deputy d = deputies.get(i);
            if(d.returnName().contains(name) && d.returnName().contains(surname))
                return deputies.get(i).returnID();
        }
        return "";

    }

    public List<Deputy> returnDeputyList()
    {
        return deputies;
    }
}