import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.IOException;
import java.net.HttpCookie;


//OD STRONY SERWERA
@WebSocket
public class ChatWebSocketHandler {
    private String sender, msg;
    private ChatSystem chat;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {

        if(chat==null)
            chat = new ChatSystem();
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        //usunac z listy z odpowiedniego kanału, albo z listy użtkowników "pozakanałowych"

        String canalNumbers = chat.getCanalList().composeCanalsNumbers();
        Canal c = chat.getCanalList().findCanalForUser(user);
        if(c!=null ) {

            //String s = Character.toString((char)0);
            String s = String.valueOf(0);
            String sender = c.getuserUsernameMap().get(user);

            chat.getCanalList().leaveCanal(user);
            if(!chat.getCanalList().isEmpty())
            c.msgLeaveCanal(canalNumbers, sender, s+"Użytkownik " + sender + " zakończył sesję.");
        }
        chat.getCanalList().leaveCanal(user);
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
                //trzeba mu wysłać listę kanałów
                chat.getCanalList().sendCanalNumbers();

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
                Json j = new Json();
                String JsonToSend = j.newJsonString(canalNumbers2,question,response);

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
