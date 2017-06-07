package socketio.demo2;

import socketio.demo1.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:  Administrator
 * Date:    2017/6/6 21:05
 * Function:Please input the function of this class!
 */
public class Server {

    final static int PORT = 8765;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server Start");
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50,1000);
            while (true){
                socket = serverSocket.accept();
                executorPool.executor(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
