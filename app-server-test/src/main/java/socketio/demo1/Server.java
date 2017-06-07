package socketio.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:  Administrator
 * Date:    2017/6/6 20:34
 * Function:Please input the function of this class!
 */
public class Server {

    final static int PORT = 8765;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            Socket accept = serverSocket.accept();
            new Thread(new ServerHandler(accept)).start();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }


    }


}
