package app.server.test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Function:
 * Author:  ilene
 * Date:    2016/7/28 16:13
 */
public class SerializableTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        //反序列化,将该对象恢复(存储到一个文件)
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.out"));
//        CallThirdException p = (CallThirdException) ois.readObject();
//        CallThirdException p = CallThirdException.newInstence();
        //p = p.read(p);
        //CallThirdException p = SerializableTest.read();

//        p.save(new HashMap<String, Object>() {
//            {
//                put("url","1231");
//                put("params","1231333333333333333");
//            }
//        });
//        System.out.println(p);
//
//        byte[] serialize = SerializableTest.serialize(p);
//        System.out.println(serialize);
        CallThirdException unserialize = SerializableTest.unserialize(SerializableTest.getContent2("data.txt"), CallThirdException.class);
        System.out.println(unserialize);
        //SerializableTest.write(p);
//        System.out.println( p);

        //序列化一个对象(存储到一个文件)
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("url","44444");
//        map.put("params", "4444");
//        CallThirdException p = CallThirdException.newInstence();
//        p.save(map);
//        SerializableTest.write(p);

    }


    public static boolean write(CallThirdException data){
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

    public static CallThirdException read(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("person.out"));
            CallThirdException data = (CallThirdException) ois.readObject();
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




    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
//        BufferedOutputStream stream = null;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
//            FileOutputStream fstream = new FileOutputStream(new File("data.ser"));
//            stream = new BufferedOutputStream(fstream);
//            stream.write(bytes);

            FileOutputStream fos = new FileOutputStream("data.txt");
            fos.write(bytes);
            fos.close();

            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T unserialize(byte[] bytes,Class<T> clazz) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            if(clazz.isInstance(object)){
                return clazz.cast(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getContent2(String filePath) throws IOException
    {
        FileInputStream in=new FileInputStream(filePath);

        ByteArrayOutputStream out=new ByteArrayOutputStream(1024);

        System.out.println("bytes available:"+in.available());

        byte[] temp=new byte[1024];

        int size=0;

        while((size=in.read(temp))!=-1)
        {
            out.write(temp,0,size);
        }

        in.close();

        byte[] bytes=out.toByteArray();
        System.out.println("bytes size got is:" + bytes.length);

        return bytes;
    }


}
