package socketio.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author:  Administrator
 * Date:    2017/6/6 20:50
 * Function:Please input the function of this class!
 */
public class Client {

    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) {

        for (int i = 0;i<1000;i++){
            Socket socket = null;
            BufferedReader in = null;
            PrintWriter out = null;

            try {
                socket = new Socket(ADDRESS,PORT);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                out.println("接收到客户端的请求数据。。。" + i);
                String response = in.readLine();
                System.out.println("Client-" + i + ":" + response);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                socket = null;
                in = null;


            }
        }



    }


}
