package com.util;


import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by loovee1 on 2016/5/27.
 */
public class ExcelUtils {


    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("D:\\nio.txt","rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buf);
            //fileChannel.read()
            //fileChannel.re
            System.out.println(bytesRead);

            System.out.println(new String(buf.array(),"UTF-8"));
            /*while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    //System.out.print((char)buf.get());
                    //buf.array();
                    System.out.println(new String(buf.array(),"UTF-8"));
                }

                buf.compact();
                bytesRead = fileChannel.read(buf);
            }*/
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    public static <T> T translateModel(String path,T t) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(path, "rw");
            FileChannel channel = aFile.getChannel();

            //WorkbookFactory.create(channel.);


            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(aFile != null){
                try {
                    aFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Class<?> aClass = t.getClass();


        //return null ;
    }


}
