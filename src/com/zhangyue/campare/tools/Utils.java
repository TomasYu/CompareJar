package com.zhangyue.campare.tools;

/**
 * Created by zy1 on 16/11/2017.
 */
public class Utils {
    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 ;
    }

}
