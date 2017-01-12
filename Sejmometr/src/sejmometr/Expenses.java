
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.ArrayList;
import java.util.List;

public class Expenses{

    private byte[] readedJson;
    private BigDecimal totalExpenses;
    private BigDecimal littleRepairs;

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


    public Expenses(String id){

        try {

            String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
            url = url.concat(id);
            url = url.concat(".json?layers[]=krs&layers[]=wydatki");
            Loader l = new Loader(new URL(url));
            readedJson = l.returnData();
            boolean pierwsza = false, druga = false, trzecia = false, czwarta = false, counted = false, end = false;

            for(int i = 0;i<readedJson.length && !counted && !end;i++)
            {
                if(readedJson[i]=='[' && czwarta && readedJson[i+1]!=']') //znalezlismy 5 nawias [, zaraz bedzie tablica wydatkow
                {
                    //if()
                    //{"pola":[
                    String s2="";
                    for(int m=i;m<i+10;m++)
                    {
                        StringBuilder temp = new StringBuilder().append((char)readedJson[m]);
                        s2=s2.concat(temp.toString());
                    }
                    if(!s2.equals("[{\"pola\":["))
                        System.out.println("Uwaga! numer: id");
                    i += 10;

                    BigDecimal total = new BigDecimal(0);
                    boolean first = true;
                    int n =0;
                    while(readedJson[i-1]!=']' || first){
                        first = false;
                        i++;
                        n++;
                        ReadOneNumberResult res = readOneNumber(i);
                        BigDecimal oneE = res.a;
                        i = res.i;
                        i += 2;
                        total = total.add(oneE);
                        if(n==13) littleRepairs = oneE;
                    }
                    while (readedJson[i] != '[' && i < readedJson.length)
                        i++;
                    i++;

                    if(readedJson[i]!='"')
                        System.out.println("Uwaga - 1 lista id"+ id);

                    else {
                        first = true;
                        n = 0;
                        while (readedJson[i - 1] != ']' || first) {
                            first = false;
                            i++;
                            n++;
                            ReadOneNumberResult res = readOneNumber(i);
                            BigDecimal oneE2 = res.a;
                            i = res.i;
                            i += 2;
                            total = total.add(oneE2);
                            if(n==13) littleRepairs = littleRepairs.add(oneE2);
                        }
                        while (readedJson[i]!='[')
                            i++;
                        if(readedJson[i+1]!=']') System.out.println("Uwaga, trzeci rocznik, id: " + id);
                    }
                    this.totalExpenses = total;
                    counted = true;
                }
                if(readedJson[i]=='[' && czwarta && readedJson[i+1]==']' && !counted) {
                    end = true;
                    System.out.println("pusta tablica" + id);
                    this.totalExpenses = new BigDecimal(0);
                }
                if(readedJson[i]=='[' && trzecia)
                    czwarta = true;
                if(readedJson[i]=='[' && druga)
                    trzecia = true;
                if(readedJson[i]=='[' && pierwsza)
                    druga = true;
                if(readedJson[i]=='[' && !pierwsza)
                    pierwsza = true;
            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public BigDecimal returnOneDeputyTotalExpenses ()
    {
        return this.totalExpenses;
    }
}