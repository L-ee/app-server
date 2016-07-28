package app.server.test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Function:
 * Author:  ilene
 * Date:    2016/7/28 11:07
 */
public class CallThirdException implements Serializable {

    private static final long serialVersionUID = 3105560890380854635L;
    private Queue<Map<String,Object>> data = new LinkedBlockingQueue<>();
    private static CallThirdException person = null;

    private CallThirdException() {
    }

    public static CallThirdException newInstence(){
        if(person == null){
            person = new CallThirdException();
            return person;
        } else {
            return person;
        }
    }

    public boolean save(Map<String,Object> map){
        return this.data.add(map);
    }

    public boolean write(CallThirdException data){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("person.out"));
            oos.writeObject(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public CallThirdException read(CallThirdException data){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("person.out"));
            data = (CallThirdException) ois.readObject();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String toString() {
        for (Map<String, Object> map : data) {
            System.out.println(map.get("url"));
            System.out.println(map.get("params"));
        }
        return "data:" + data ;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        //反序列化,将该对象恢复(存储到一个文件)
        //ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.out"));
        //CallThirdException p = (CallThirdException) ois.readObject();
        CallThirdException p = CallThirdException.newInstence();
        p = p.read(p);
        System.out.println( p);
//        p.save(new HashMap<String, Object>() {
//            {
//                put("url","1231");
//                put("params","1231333333333333333");
//            }
//        });
//        p.write(p);
//        System.out.println( p);

        //序列化一个对象(存储到一个文件)
        /*Map<String,Object> map = new HashMap<String,Object>();
        map.put("url","44444");
        map.put("params", "4444");
        CallThirdException p = CallThirdException.newInstence();
        p.save(map);
        p.write(p);*/

    }
}
