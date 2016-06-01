package app.server.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by loovee1 on 2016/3/30.
 */
public class Test {

    public static void main(String[] args) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("E:/me/web.txt","rw");

            FileChannel channel = file.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(70);

            int read = channel.read(byteBuffer);

            while (read != -1) {
                System.out.println("Read:" + read);
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get());
                }
                System.out.println();
            }

            byteBuffer.clear();
            read = channel.read(byteBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(file != null){
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
