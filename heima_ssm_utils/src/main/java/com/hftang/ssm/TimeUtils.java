package com.hftang.ssm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hftang
 * @date 2019-06-11 15:24
 * @desc
 */
public class TimeUtils {

    //将Date 转成 字符串

    public static String Date2Str(Date date, String patter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //将字符串转成 Date
    public Date str2Date(String str, String patter) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);
        Date date = simpleDateFormat.parse(str);
        return date;
    }
}
