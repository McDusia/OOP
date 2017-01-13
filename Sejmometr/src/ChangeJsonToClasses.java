
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ChangeJsonToClasses {

    byte[] readedJson;
    private List <Deputy> deputyList;

    public ChangeJsonToClasses()
    {
        deputyList = new ArrayList<>();
    }

    public void changeToDeputyList(Loader loaded){


            readedJson=loaded.returnData();

            boolean pierwsza = false, druga = false;
            String lastID ="";
            for(int i=0;i<readedJson.length;i++)
            {


                if(readedJson[i]=='{' && druga)
                {
                    Deputy readedDeputy = new Deputy();
                    readedDeputy.addId(lastID);
                    while(readedJson[i]!=':' && i<readedJson.length)
                         i++;
                    i++;
                    while(readedJson[i]!=':' && i<readedJson.length)
                        i++;
                    i+=2; //omitting "
                    String s="";
                    while(readedJson[i]!='"' && i< readedJson.length)
                    {
                        boolean unicodeSign = false;
                        char sign;
                        char c = 'r';
                        if(readedJson[i]==92)
                        {
                            String s2="\\";
                            i++;
                            for(int j=0;j<5;j++,i++)
                            {
                                StringBuilder temp = new StringBuilder().append((char)readedJson[i]);
                                s2=s2.concat(temp.toString());
                            }
                            //String s3 = "\\u0142";

                            c = (char) Integer.parseInt( s2.substring(2), 16 );
                            unicodeSign = true;
                            i--;
                        }

                        if(!unicodeSign)  sign = (char)readedJson[i];
                        else sign = c;

                        StringBuilder signSB =new StringBuilder().append(sign);
                        String signS = signSB.toString();
                        s=s.concat(signS);
                        i++;
                    }

                    readedDeputy.addName(s);
                    //System.out.println(s);
                    deputyList.add(readedDeputy);

                    //{"ludzie.id":"186","ludzie.nazwa":"Piotr
                    druga = false;
                }
                if(readedJson[i]=='{' && pierwsza && !druga) {
                    druga = true;
                    while(readedJson[i]!=':' && i<readedJson.length)
                        i++;
                    i+=2; //omitting first "
                    String s ="";
                    //byte b = readedJson[i];
                    while(readedJson[i]!='"' && i<readedJson.length)
                    {	char cos;
                        cos = (char)readedJson[i];
                        StringBuilder cos2 =new StringBuilder().append(cos);
                        s=s.concat(cos2.toString());
                        i++;
                    }
                    lastID=s;
                }
                if(readedJson[i]=='{' && !pierwsza) pierwsza=true;
            }

    }
    public List<Deputy> returnDeputyList()
    {
        return this.deputyList;
    }


}