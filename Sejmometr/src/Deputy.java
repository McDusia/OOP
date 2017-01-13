import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class Deputy {

    private String id;
    private String name;
    private String wydatkiNaNaprawy;
    int kadencja;
    private int liczba_wyjazdow;
    private byte[] readedJson;  //json z wyjazdami
    private byte[] readedJsonExpenses;
    private BigDecimal totalExpenses;
    private BigDecimal littleRepairs;

    public int getLiczbaWyjazdow() {
        int liczba = this.liczba_wyjazdow;
        return liczba;
    }

    public BigDecimal getLittleRepairs() {
        return littleRepairs;
    }
    public byte[] getReadedJson(){
        return readedJson;
    }

    public byte[] getReadedJsonExpenses(){
        return readedJsonExpenses;
    }

    public Deputy() {
        id = "";
        name = "";
    }

    public void addId(String readedId) {
        this.id = readedId;
    }

    public void addName(String readedName) {
        this.name = readedName;
    }

    public String returnName() {
        return this.name;
    }

    public String returnID() {
        return this.id;
    }

    private int getNextQuatationPos(int i) {
        i++;
        while (readedJson[i] != '"')
            i++;
        return i;
    }

    private boolean isEqual(int index, String s) {
        for (int i = 0; i < s.length(); i++)
            if (readedJson[index + i] != s.charAt(i))
                return false;
        return true;
    }

    private int indexOf(String s) //reports the zero based index of the first occurrence of the specified string
    {
        return indexOf(s,0);
    }
    private int indexOf(String s, int start)
    {
        for (int i = start; i < readedJson.length; i++)
            if (isEqual(i, s)) return i;
        return -1;
    }

    private boolean found(int i) //trzeba zaktualizowac indeks
    {
        //"poslowie.liczba_wyjazdow"
        String tab = "poslowie.liczba_wyjazdow";
        //System.out.println(tab.length());
        int j = 0;
        //StringBuilder s = new StringBuilder("");
        //String s2 = "poslowie.liczba_wyjazdow";

        while (j < 24 && i < readedJson.length) {
            if (readedJson[i] != tab.charAt(j)) return false;
            i++;
            j++;
            //s = s.append((char) readedJson[i]);
        }
        //if((String)s!=s2) return false;
        return true;
    }

    public void loadTravels()
    {
        try {
            String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
            url = url.concat(id);
            url = url.concat(".json?layers[]=krs&layers[]=wyjazdy");
            Loader l = new Loader(new URL(url));
            readedJson = l.returnData();
        } catch(MalformedURLException ex){
        System.out.println("Niepoprawny format adresu url");
        }
        catch(IOException ex){
        System.out.println("Problem z adresem");
        }
    }

    public void loadExpenses()
    {
        try {

            String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
            url = url.concat(id);
            url = url.concat(".json?layers[]=krs&layers[]=wydatki");
            Loader l = new Loader(new URL(url));
            readedJsonExpenses = l.returnData();

        } catch (MalformedURLException e) {
            System.out.println("Niepoprawny format adresu url");
        }
         catch (IOException e)
        {
            System.out.println("Problem z adresem");
        }

    }

    public class ReadOneNumberResult
    {
        public int i;
        public BigDecimal a;
    }

    private ReadOneNumberResult readOneNumber(int i) //f read whole quote and returns it
    {
        String oneExpense="";
        while(readedJsonExpenses[i]!='"')
        {
            StringBuilder temp = new StringBuilder().append((char)readedJsonExpenses[i]);
            oneExpense=oneExpense.concat(temp.toString());
            i++;
        }
        BigDecimal oneE = new BigDecimal(oneExpense);
        ReadOneNumberResult result = new ReadOneNumberResult();
        result.i = i;
        result.a = oneE;
        return result;
    }

    public void calculateTotalExpenses()
    {
        boolean pierwsza = false, druga = false, trzecia = false, czwarta = false, counted = false, end = false;
        for(int i = 0;i<readedJsonExpenses.length && !counted && !end;i++)
        {
            if(readedJsonExpenses[i]=='[' && czwarta && readedJsonExpenses[i+1]!=']') //znalezlismy 5 nawias [, zaraz bedzie tablica wydatkow
            {
                //if()
                //{"pola":[
                String s2="";
                for(int m=i;m<i+10;m++)
                {
                    StringBuilder temp = new StringBuilder().append((char)readedJsonExpenses[m]);
                    s2=s2.concat(temp.toString());
                }
                //if(!s2.equals("[{\"pola\":["))
                   // System.out.println("Uwaga! numer: id");
                i += 10;

                BigDecimal total = new BigDecimal(0);
                boolean first = true;
                int n =0;
                    while (readedJsonExpenses[i - 1] != ']' || first) {
                        first = false;
                        i++;
                        n++;
                        ReadOneNumberResult res = readOneNumber(i);
                        BigDecimal oneE = res.a;
                        i = res.i;
                        i += 2;
                        total = total.add(oneE);
                        if (n == 13) littleRepairs = oneE;
                    }
                while (readedJsonExpenses[i] != '[' && i < readedJsonExpenses.length)
                    i++;
                i++;

                if(readedJsonExpenses[i]!='"')
                    System.out.println("Uwaga - 1 lista id"+ id);

                else {
                    first = true;
                    n = 0;
                    while (readedJsonExpenses[i - 1] != ']' || first) {
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
                    while (readedJsonExpenses[i]!='[')
                        i++;
                    if(readedJsonExpenses[i+1]!=']') System.out.println("Uwaga, trzeci rocznik, id: " + id);
                }
                this.totalExpenses = total;
                counted = true;
            }
            if(readedJsonExpenses[i]=='[' && czwarta && readedJsonExpenses[i+1]==']' && !counted) {
                end = true;
                //System.out.println("pusta tablica" + id);
                this.totalExpenses = new BigDecimal(0);
            }
            if(readedJsonExpenses[i]=='[' && trzecia)
                czwarta = true;
            if(readedJsonExpenses[i]=='[' && druga)
                trzecia = true;
            if(readedJsonExpenses[i]=='[' && pierwsza)
                druga = true;
            if(readedJsonExpenses[i]=='[' && !pierwsza)
                pierwsza = true;
        }
    }

    public BigDecimal OneDeputyTotalExpenses ()
    {
        return this.totalExpenses;
    }

    public void calculateNumberOfTrips()
    {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < readedJson.length; i++)
        {
            if (readedJson[i] == 'p' && found(i))
            {
                i = i + 26;//przesuwam o dlugosc stringa poslowie.listawyjazdow i 2 zeby przejsc " i :
                while (readedJson[i] != ',')
                {
                    int part = Integer.valueOf(readedJson[i]) - 48;
                    String string = String.valueOf(part);
                    tmp.append(string);
                    i++;
                }
                this.liczba_wyjazdow = Integer.valueOf(tmp.toString());
            }
        }
    }

    private String getNextQuotedValue(int start)
    {
        int i = indexOf("\"",start) + 1;
        String s = "";
        for (;i< readedJson.length && readedJson[i]!= '\"';i++)
        {
            //t = Integer.valueOf(readedJson[i]) - 48;
            //StringBuilder string = new String(readedJson[i]);
            s += Character.toString((char)readedJson[i]);
        }
        return s;
    }

    public boolean wasInItaly() {
        if(indexOf("\"kraj\":\"W\\u0142ochy\"")==-1)
            return false;
        else
            return true;
    }

    public int daysAbroadCount ()
    {
        int sum=0;
        String pattern = "\"liczba_dni\":";
        int index=0;
        while((index=indexOf(pattern,index))!=-1)
        {
            // Move index beyond the end of string "liczba dni"
            index += pattern.length();
            String val=getNextQuotedValue(index);
            sum += Integer.valueOf(val);
        }
        return sum;
    }

    public BigDecimal maxTravelExpense()
    {
        String pattern = "\"koszt_suma\"";
        BigDecimal max = new BigDecimal(0);
        int index=0;
        while((index=indexOf(pattern,index))!=-1)
        {
            // Move index beyond the end of string "liczba dni"
            index += pattern.length();
            String val=getNextQuotedValue(index);
            BigDecimal oneTravelExpense = new BigDecimal(val);
            max = max.max(oneTravelExpense);
        }
        return max;
    }
}