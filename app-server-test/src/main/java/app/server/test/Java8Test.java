package app.server.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Function:
 * Author:  ilene
 * Date:    2016/6/6 16:44
 */
public class Java8Test {


    public static void main(String[] args) {

        /*List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i<20 ; i++){
            list.add(i);
        }*/

        List<String> str = Arrays.asList("Methed 1", "Methed 2", "Methed 3", "Methed 4", "Methed 5", "Methed 6");

//      str.forEach(n -> System.out.println(n));
        str.forEach(System.out::println);


    }


}
