package com.zhangyue.campare.tools;

import java.io.File;

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

    public static String getCurPath(){
        File   file=new File(".");
        String path=file.getAbsolutePath();
        return path;
    }

}
