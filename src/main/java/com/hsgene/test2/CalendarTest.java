package com.hsgene.test2;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hjc on 2017/1/16.
 */
public class CalendarTest {

    /**
     * 获取当月的第一天的 00:00:00
     */
    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(calendar.getTime()));  //      Calendar转为Date
        calendar.setTime(new Date());   //  Date转为Calendar
        System.out.println(dateFormat.format(calendar.getTime()));
    }
}
