
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import java.net.URL;
import java.nio.file.Path;
//import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.net.ssl.HttpsURLConnection;

public class Loader{

    int length, code;
    String type, encoding, msg;
    Map<String, List<String>> headers;
    private byte[] data; //tablica bajtów odczytanych ze strony www
    InputStream errorStream = null;
    public final int READ_CHUNK_SIZE = 8192; // 8kB
    Path file = Paths.get("C:\\Users\\Madzia\\IdeaProjects\\Test2\\src\\wynik.txt");

    public Loader(URL url) throws IOException {
        HttpsURLConnection conn = null;
        try {
            conn = (HttpsURLConnection)url.openConnection();

            //conn = (HttpURLConnection) url.openConnection();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("URL protocol must be HTTP.");
        }
        // Set up a request.
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("User-agent", "Java Spider");

        try {
            conn.connect();

            code      = conn.getResponseCode();
            msg       = conn.getResponseMessage();
            headers  = conn.getHeaderFields();
            length    = conn.getContentLength();
            type      = conn.getContentType();
            encoding = conn.getContentEncoding();

            if (encoding == null) {
                Pattern p = Pattern.compile("(; )?charset=(.+)");
                Matcher m = p.matcher(type);
                if (m.find()) {
                    encoding = m.group(2);
                }
            }
            InputStream stream = conn.getInputStream(); //z polaczenia sczytujemy strumien danych
            errorStream   = conn.getErrorStream();
            data = new byte[Math.max(length, stream.available())];
            byte[] buffer = new byte[READ_CHUNK_SIZE];
            int total, n;
            total = n = stream.read(data);
            for (n = stream.read(buffer); n != -1; n = stream.read(buffer), total += n) {
                byte[] new_data = new byte[data.length + n];
                System.arraycopy(data, 0, new_data, 0, data.length);
                System.arraycopy(buffer, 0, new_data, data.length, n);
                data = new_data;
            }
        } finally {
            //OutputStream out = Files.newOutputStream(file);
            //int i= 0;
            String line;
            //while(i<data.length)
            //line = data.toString();
            //out.write(data);
            //i++;
            conn.disconnect();
        }
    }

    public byte[] returnData()
    {
        return this.data;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getContentType() {
        return type;
    }

    public int getContentLength() {
        return length;
    }

    public int getResponseCode() {
        return code;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headers;
    }

    public InputStream getErrorStream() {
        return errorStream;
    }
}