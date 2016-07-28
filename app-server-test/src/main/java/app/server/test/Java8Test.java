package app.server.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        List<String> str = Arrays.asList("Methed 1", "Methed 2", "Methed 3", "Methed 4", "Methed 5", "Methed 6");

//      str.forEach(n -> System.out.println(n));
//        str.forEach(System.out::println);

        String a = "123,125,589,65,25";

        Pattern compile = Pattern.compile("^[0-9]+$");
        //System.out.println(a.contains(",25,"));
        //System.out.println(a.startsWith(",123"));
        Matcher matcher = compile.matcher("1233dd223");
        System.out.println(matcher.find());

        //System.out.println(117 == new Integer(null).intValue());



    }


}
