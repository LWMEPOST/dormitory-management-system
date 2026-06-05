package com.dormitory.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateUtil {
    
    // 日期格式：yyyy-MM-dd
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    // 日期时间格式：yyyy-MM-dd HH:mm:ss
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 将日期转换为字符串
     * @param date 日期对象
     * @param format 格式
     * @return 日期字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    /**
     * 将日期转换为字符串（yyyy-MM-dd）
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMAT);
    }
    
    /**
     * 将日期转换为字符串（yyyy-MM-dd HH:mm:ss）
     * @param date 日期对象
     * @return 日期时间字符串
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DATETIME_FORMAT);
    }
    
    /**
     * 将字符串转换为日期
     * @param dateStr 日期字符串
     * @param format 格式
     * @return 日期对象
     * @throws ParseException 解析异常
     */
    public static Date parseDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }
    
    /**
     * 将字符串转换为日期（yyyy-MM-dd）
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws ParseException 解析异常
     */
    public static Date parseDate(String dateStr) throws ParseException {
        return parseDate(dateStr, DATE_FORMAT);
    }
    
    /**
     * 将字符串转换为日期（yyyy-MM-dd HH:mm:ss）
     * @param dateStr 日期字符串
     * @return 日期对象
     * @throws ParseException 解析异常
     */
    public static Date parseDateTime(String dateStr) throws ParseException {
        return parseDate(dateStr, DATETIME_FORMAT);
    }
    
    /**
     * 获取当前日期
     * @return 当前日期字符串（yyyy-MM-dd）
     */
    public static String getCurrentDate() {
        return formatDate(new Date());
    }
    
    /**
     * 获取当前日期时间
     * @return 当前日期时间字符串（yyyy-MM-dd HH:mm:ss）
     */
    public static String getCurrentDateTime() {
        return formatDateTime(new Date());
    }
}
