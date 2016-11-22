package app.server.pipe;

import java.io.PipedInputStream;

/**
 * Author:  Administrator
 * Date:    2016/11/6 21:51
 * Function:Please input the function of this class!
 */
public class ReceiveMsg implements Runnable {

    private PipedInputStream in;

    public ReceiveMsg() {
        this.in = new PipedInputStream();
    }

    public PipedInputStream getIn() {
        return in;
    }

    public void receive(){

    }

    @Override
    public void run() {

    }
}
