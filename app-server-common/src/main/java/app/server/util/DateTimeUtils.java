package app.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Function: 日期时间相关工具方法
 * Author:  Lee
 * Date:    2016/8/17 10:47
 */
public class DateTimeUtils {

    /**
     * 计算两个时间相隔的天数
     * @param min
     * @param max
     * @return
     */
    public static int calculate(Date min,Date max){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            min = sdf.parse(sdf.format(min));
            max = sdf.parse(sdf.format(max));
            Calendar cal = Calendar.getInstance();
            cal.setTime(min);
            long time1 = cal.getTimeInMillis();
            cal.setTime(max);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2-time1)/(1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
