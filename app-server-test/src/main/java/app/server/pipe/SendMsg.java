package app.server.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Author:  Administrator
 * Date:    2016/11/6 21:23
 * Function:Please input the function of this class!
 */
public class SendMsg implements Runnable {


    private PipedOutputStream out;

    public SendMsg() {
        this.out = new PipedOutputStream();
    }

    public PipedOutputStream getOut() {
        return out;
    }

    public void send(){
        String str = "start";

        try {
            out.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null){

                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }


    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for signal...");
            Thread.sleep(2000);
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
