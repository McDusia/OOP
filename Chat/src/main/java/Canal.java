import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static j2html.TagCreator.*;
import static j2html.TagCreator.span;

public class Canal {

    private int number;
    private Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    //private int nextUserNumber = 1;

    public Canal(int n)
    {
        number = n;
    }

    public boolean isUserInCanal(Session s)
    {
      return userUsernameMap.containsKey(s);
    }

    public void removeUserFromCanal(Session s)
    {
        userUsernameMap.remove(s);
    }
    public Map<Session,String> getuserUsernameMap()
    {
        return userUsernameMap;
    }
    public void addUserToCanal(Session s,String username)
    {
        userUsernameMap.put(s,username);
    }

    public void broadcastMessage(String canalNumbers, String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(newJsonString(canalNumbers,sender,message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String newJsonString(String a, String b, String c)
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
