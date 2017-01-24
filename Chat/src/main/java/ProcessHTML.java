import spark.Request;

import java.util.Map;

public class ProcessHTML {
    public String ProcessRequest(Request req)
    {

        Map<String,String> map=req.cookies();
        for(String key: map.keySet())
            System.out.println(key + " - " + map.get(key));

        return "Request processed.";
    }
}
