package app.server.test;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Function:
 * Author:  ilene
 * Date:    2016/7/29 10:58
 */
public class ThirdErrContainer {

    public static Queue<Map<String,Object>> data = null;

    static {
        data = new LinkedBlockingQueue<>();
    }

    public static synchronized boolean save(Map<String,Object> map){
        return data.add(map);
    }

    public static String transfer(Queue<Map<String,Object>> queue){
        return JSON.toJSONString(queue);
    }

    public static Queue<Map<String,Object>> reverse(String string,Queue<Map<String,Object>> queue){
        return JSON.parseObject(string, queue.getClass());
    }

    public static boolean write(String content,String path){
//        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(path);
            o.write(content.getBytes("utf-8"));
//            mm=new RandomAccessFile(fileName,"rw");
//            mm.writeBytes(content);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            /*if(mm!=null){
                mm.close();
            }*/

            if(o != null){
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static String read(String path){
        StringBuilder result = new StringBuilder();
        FileReader fileReader = null;
        BufferedReader bufferedReader=null;
        try{
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            String read = null;
            while((read=bufferedReader.readLine())!= null){
                result.append(read);
            }
            System.out.println(new Throwable().getStackTrace()[1].getMethodName());
            new File(path).delete();
            return result.toString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileReader!=null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
/*        Queue<Map<String, Object>> queue = ThirdErrContainer.data;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("url","222255");
        map.put("params","44444555");
        queue.add(map);
        String transfer = ThirdErrContainer.transfer(queue);
        System.out.println(transfer);*/
        //System.out.println(ThirdErrContainer.write(transfer));
        System.out.println(ThirdErrContainer.read("data.txt"));
        Queue<Map<String,Object>> reverse = ThirdErrContainer.reverse(ThirdErrContainer.read("data.txt"), new LinkedBlockingQueue<>());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("url","222255");
        map.put("params","44444555");
        reverse.add(map);
        System.out.println(ThirdErrContainer.write(ThirdErrContainer.transfer(reverse),"data.txt"));
        System.out.println(ThirdErrContainer.read("data.txt"));

        /*for (Map<String, Object> m : reverse) {
            System.out.println(m.get("url"));
            System.out.println(m.get("params"));
        }*/
    }
}
