package com.leekli.javase.calendar;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 时间戳表示全球绝对时间
 * date 和 datetime 表示本地的时间显示，如果全球绝对时间为1511342621673,转换为北京时间为：
 * @author media-liwei
 *
 */
public class CalendarDemo {
    
    public static long getExpiredTime(int fileExpiresHours){
        Calendar calendar;
        try {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, fileExpiresHours + 1);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 000);
            return calendar.getTimeInMillis();
        } catch (Exception e) {
            return 0;
        }
    }
    private static void test() {
        //全球绝对时间
    	long time = System.currentTimeMillis();
    	long time2 = Calendar.getInstance().getTimeInMillis();
    	long time3 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai")).getTimeInMillis();
    	System.out.println("全球绝对时间:"+time);
    	System.out.println("全球绝对时间:"+time2);
    	System.out.println("全球绝对时间:"+time3);
    	
    	//每个时区的显示时间
    	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));//得到该时区的时间，并非时间戳
    	System.out.println("GMT:"+DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss"));
    	
    	Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));//得到该时区的时间，并非时间戳
    	System.out.println("中国:"+DateFormatUtils.format(calendar2, "yyyy-MM-dd HH:mm:ss"));
    }
}
