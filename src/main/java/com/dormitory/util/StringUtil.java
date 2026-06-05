package com.dormitory.util;

/**
 * 字符串处理工具类
 */
public class StringUtil {
    
    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return true-为空，false-不为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }
    
    /**
     * 判断字符串是否不为空
     * @param str 字符串
     * @return true-不为空，false-为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 生成6位随机验证码
     * @return 随机验证码
     */
    public static String generateVerifyCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
}
