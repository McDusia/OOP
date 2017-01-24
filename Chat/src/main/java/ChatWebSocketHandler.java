import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpCookie;


//OD STRONY SERWERA
@WebSocket
public class ChatWebSocketHandler {
    private String sender, msg;
    private ChatSystem chat;
    public ChatWebSocketHandler()
    {
        chat = new ChatSystem();
    }

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        //String username = "User" + Canal.nextUserNumber++;
        HttpCookie cookie1 = new HttpCookie("PierszeCiastko", "1,2,3");//cl.getList().toString());
        user.getUpgradeRequest().getCookies().add(cookie1);
        //CanalList.userUsernameMap.put(user, username);
        //CanalList.messageToNew(user);
        //CanalList.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        //String username = Canal.userUsernameMap.get(user);
        //Canal.userUsernameMap.remove(user);
        //Canal.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        switch (message.codePointAt(0)){
            case 1:
                //serwer dostał imię od nowego klienta
                message = message.substring(1, message.length());
                HttpCookie cookie = new HttpCookie("userName", message);
                user.getUpgradeRequest().getCookies().add(cookie);
                chat.getCanalList().adduserBeyondCanal(user, message);
                chat.getCanalList().sendCanalNumbers();
                //ChatSystem.list.composeCanalsNumbers();
                //trzeba mu wysłać listę kanałów
                break;
            case 2:
                //użytkownik chce utworzyć nowy kanał
                chat.getCanalList().createNewCanal();
                chat.getCanalList().sendCanalNumbers();
                break;
            case 3:
                //użytkownik wybrał kanał
                Integer nr = (Integer.valueOf (message.substring(1, message.length())));
                chat.getCanalList().addUserToCanal(user,nr);
                break;
            case 4:
                //użytkownik opuścił kanał
                chat.getCanalList().leaveCanal(user);
                chat.getCanalList().sendCanalNumbers();
                break;
            case 5:
                //użytkownik wysłał wiadomość na danym kanale
                String canalNumbers = chat.getCanalList().composeCanalsNumbers();
                Canal c = chat.getCanalList().findCanalForUser(user);
                if(c!=null)
                    c.broadcastMessage(canalNumbers,sender = c.getuserUsernameMap().get(user), msg = message);
                break;
            case 6:
                //użytkownik wybrał chatbot
                String canalNumbers2 = chat.getCanalList().composeCanalsNumbers();
                String question = message.substring(1, message.length());
                String response ="";
                Chatbot bot = new Chatbot();
                switch (question)
                {
                    case "time":
                        response = bot.getTime();
                        break;
                    case "weekday":
                        response = bot.getDay();
                        break;
                    case "weather":
                        response = bot.getWeatherInfo();
                        break;
                }
                String JsonToSend = chat.getCanalList().newJsonString(canalNumbers2,question,response);
                try {
                    user.getRemote().sendString(JsonToSend);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
}
