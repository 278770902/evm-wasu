package com.evmtv.cloudvideo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    /**
     * @param milSecond
     * @param type      yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String DateLongToString(long milSecond, String type) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    /**
     * @param date
     * @param type yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static Long DateStringToLong(String date, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        try {
            return dateFormat.parse(date).getTime();
        } catch (ParseException e1) {
            return null;
        }
    }

    /**
     * @param time 播放时长
     * @return
     */
    public static String IntToStringTime(int time) {
        StringBuffer sb = null;
        time = time / 1000;
        int m = (time % 3600) / 60;
        if (m < 10) {
            sb = new StringBuffer("0" + m);
        } else {
            sb = new StringBuffer("" + m);
        }
        sb.append(":");
        int s = (time % 3600) % 60;
        if (s < 10) {
            sb.append("0" + s);
        } else {
            sb.append(s);
        }
        return sb.toString();
    }
}
