package app.server.util;

import java.util.Random;

/**
 * Function: 随机数的应用工具类
 * Author:  Lee
 * Date:    2016/6/17 17:35
 */
public class RamdomUtils {


    /**
     * 随机产生一定范围的随机数
     * @param min
     * @param max
     */
    public static int getRangeNumber(int min,int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

}
