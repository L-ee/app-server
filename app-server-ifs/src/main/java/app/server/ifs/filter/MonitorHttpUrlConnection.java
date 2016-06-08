package app.server.ifs.filter;


import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import sun.net.www.MessageHeader;
import sun.net.www.protocol.http.Handler;
import sun.net.www.protocol.http.HttpURLConnection;

public class MonitorHttpUrlConnection extends HttpURLConnection {

    public MonitorHttpUrlConnection(URL url) throws IOException {
        this(url, new Handler());
    }

    protected MonitorHttpUrlConnection(URL url, Handler handler) throws IOException {
        super(url, handler);
    }

    public MessageHeader messageHeader() {
        try {
            Field field = HttpURLConnection.class.getDeclaredField("responses");
            field.setAccessible(true);
            MessageHeader obj = (MessageHeader) field.get(this);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void disconnect() {
        // super.http.finished(); // 这里可以真正的去关闭连接Socket !
        super.disconnect();
    }
}