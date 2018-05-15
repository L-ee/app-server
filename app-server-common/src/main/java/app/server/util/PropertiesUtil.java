package app.server.util;

import java.io.*;
import java.util.*;

/**
 * Function: 读取properties类型文件
 * Author:  Lee
 * Date:    2016/6/17 17:35
 */
public class PropertiesUtil {

    // 内容容器
    private Properties props = new Properties();
    // 文件路径
    private String path;

    public PropertiesUtil(String path){
        this.path = path;
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            props.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据Key获取value的值
     * @param key
     * @return
     */
    public String getValue(String key) {
        return props.getProperty(key);
    }

    /**
     * 添加数据
     * @param key
     * @param value
     */
    public void writeData(String key, String value) {
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            this.props.setProperty(key, value);
            this.props.store(fos, "Update '" + key + "' value");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 返回所有数据的集合
     * @return
     */
    public List<Map<String,String>> getDataToList(){
        List<Map<String, String>> list = new ArrayList();
        Set<Map.Entry<Object, Object>> entries = this.props.entrySet();
        for (final Map.Entry<Object, Object> entry : entries) {
            list.add(new HashMap<String, String>() {
                {
                    put((String) entry.getKey(), (String) entry.getValue());
                }
            });
        }
        return list;
    }

    public static void main(String[] args) {
        //PropertiesUtil util = new PropertiesUtil("D:/test.properties");
        //System.out.println(util.getValue("test"));
        //util.writeData("yyuu","iioo");
        //List<Map<String, String>> properties = util.getDataToList();
        //System.out.println("=========");
        //Integer i = null;
        //System.out.println((Integer)i);
        String c = "cc";
        String d = new String("cc");

        System.out.println(c == d);
        System.out.println(c.equals(d));


    }
}
