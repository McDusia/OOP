import org.json.JSONObject;

/**
 * Created by Madzia on 2017-01-24.
 */
public class Json {



    public String newJsonString(String a, String b, String c)
    {
        try {
            return String.valueOf(new JSONObject()
                    .put("canalList", a)
                    .put("sender", b)
                    .put("message", c));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
