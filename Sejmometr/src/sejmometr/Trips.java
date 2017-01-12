
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.ArrayList;
import java.util.List;

public class Trips{

    private byte[] readedJson;


    public class ReadOneNumberResult
    {
        public int i;
        public BigDecimal a;
    }
    private ReadOneNumberResult readOneNumber(int i) //f read whole quote and returns it
    {
        String oneExpense="";
        while(readedJson[i]!='"')
        {
            StringBuilder temp = new StringBuilder().append((char)readedJson[i]);
            oneExpense=oneExpense.concat(temp.toString());
            i++;
        }
        BigDecimal oneE = new BigDecimal(oneExpense);
        ReadOneNumberResult result = new ReadOneNumberResult();
        result.i = i;
        result.a = oneE;
        return result;

    }
    private int getNextQuatationPos(int i)
    {
        i++;
        while(readedJson[i]!='"')
            i++;
        return i;
    }
    private boolean found(int i) //trzeba zaktualizowac indeks
    {
        //"poslowie.liczba_wyjazdow"
        String tab ="poslowie.liczba_wyjazdow";
        System.out.println(tab.length);
        int j = 0;
        //StringBuilder s = new StringBuilder("");
        //String s2 = "poslowie.liczba_wyjazdow";

        while(j<24 && i < readedJson.length)
        {
            if(readedJson[i]!=tab[j]) return false;
            i++;
            j++;
            //s = s.append((char) readedJson[i]);
        }
        //if((String)s!=s2) return false;
        return true;
    }


    public Trips(String id){

        try {

            String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
            url = url.concat(id);
            url = url.concat(".json?layers[]=krs&layers[]=wyjazdy");
            Loader l = new Loader(new URL(url));
            readedJson = l.returnData();
            boolean pierwsza = false, druga = false, trzecia = false, czwarta = false, counted = false, end = false;
            for(int i=0;i<readedJson.length;i++)
            {
                if(readedJson[i]=='p' && found(i))
                {
                    for(int j = 0;j<24;j++)
                        System.out.print((char)readedJson[i]);
                    //boolean foundedTripField = found(i);
                }

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}