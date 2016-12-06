package app.server.test;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Function:
 * Author:  ilene
 * Date:    2016/6/6 16:44
 */
public class Java8Test {

    public static String test(){
        return null;
    }

    public static void main(String[] args) {

        String test = test();
        System.out.println(StringUtils.isEmpty(test));

        if(!StringUtils.isEmpty(test)){
            System.out.println("dddd");
        }



    }


}
