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


    public String toString() {
        for (Map<String, Object> map : data) {
            System.out.println(map.get("url"));
            System.out.println(map.get("params"));
        }
        return "data:" + data ;
    }
}
