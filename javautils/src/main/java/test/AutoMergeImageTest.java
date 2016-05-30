package test;

import java.io.*;

/**
 * Created by loovee on 2016/3/30.
 */
public class AutoMergeImageTest {
    public static void main(String args[]) throws ClassNotFoundException {
        try {

            Peoole o = new Peoole();
            o.setId(12);
            o.setName("oopiu");
            o.setPassword("123456");

            System.out.println(o.toString());
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(new File("E:\\img\\people.txt")));
            obj.writeObject(o);
            obj.flush();
            obj.close();


            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("E:\\img\\people.txt")));
            o = (Peoole)in.readObject();

            System.out.println(o.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
