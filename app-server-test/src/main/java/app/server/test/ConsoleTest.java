package app.server.test;

import java.io.*;

/**
 * Author:  Administrator
 * Date:    2016/11/6 14:37
 * Function:Please input the function of this class!
 */
public class ConsoleTest {


//    public static void main(String[] args) throws IOException {
//
//        byte[] input = new byte[1024];
//
//        System.in.read(input);
//        System.out.println("U write : " + new String(input));
//
//
//
//    }

//    public static void main(String[] args) {
//
//        File file = new File("D:/workspace/spring-boot");
//        File[] files = file.listFiles();
//        for (File file1 : files) {
//            System.out.println(file1.getName());
//            if(file1.isDirectory()){
//                File[] f = file1.listFiles();
//                for (File file2 : f) {
//                    System.out.println(file2.getName());
//                }
//            }
//
//        }

//    public static void main(String[] args) throws IOException {
//
//
//        File file = new File("E:/test_db.txt");
//        FileInputStream in = new FileInputStream(file);
//        byte[] buf = new byte[(int) file.length()];
//
//        for (int i = 0; i < buf.length; i++) {
//            buf[i] = (byte) in.read();
//        }
//
//
//        in.close();
//
//
//        System.out.println("read : " + new String(buf,"utf-8"));
//
//    }

//    public static void main(String[] args) throws IOException {
//
//        String str = "";
//
//        File file = new File("E:/test_bb.txt");
//
//        FileOutputStream out = new FileOutputStream(file,true);
//        out.write(str.getBytes());
//
//        out.close();
//
//
//
//    }

    public static void main(String[] args) throws IOException {

        File f1 = new File("E:/test_bb.txt");
        File f2 = new File("E:/copy_test_bb.txt");


        BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(f1));
        BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(f2));

        byte[] buf = new byte[1024];
        while (bufIn.read(buf) != -1){
            bufOut.write(buf);
        }

        bufIn.close();
        bufOut.flush();
        bufOut.close();



    }







}
