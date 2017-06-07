package socketio.demo2;

import java.nio.IntBuffer;

/**
 * Author:  Administrator
 * Date:    2017/6/7 23:26
 * Function:Please input the function of this class!
 */
public class TestBuffer {


    public static void main(String[] args) {

        // 1)
        IntBuffer buf = IntBuffer.allocate(10);
        buf.put(13);
        buf.put(21);
        buf.put(35);

        System.out.println("使用fli");


    }




}
