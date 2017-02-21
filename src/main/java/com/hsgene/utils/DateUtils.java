package com.hsgene.utils;

import java.util.Date;

//  无法多继承，所以以这种方式将静态方法引入
import static org.apache.commons.lang3.time.DateFormatUtils.format;

/**
 * Created by hjc on 2017/2/21.
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String formatDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }
}
