package socketio.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author:  Administrator
 * Date:    2017/6/6 20:40
 * Function:Please input the function of this class!
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String body = null;
            while (true){
                body = in.readLine();
                if (body == null) break;
                System.out.println("Server: " + body);
                out.println("服务器响应回来的响应数据");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
