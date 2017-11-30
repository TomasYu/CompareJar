package com.zhangyue.campare;

import com.zhangyue.campare.tools.Constant;
import com.zhangyue.campare.tools.Log;

/**
 * Created by zy1 on 15/11/2017.
 * 程序执行入口
 * 需要传两个参数，不满足直接返回
 */
public class CompareEnter {
    static String first_jar_path = "C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin.jar";
    static String second_jar_path = "C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin2.jar";

    public static void main(String[] args) {
        if (!argsCheck(args)) {
            Log.d(Constant.MSG_ERROR_ILLEGAL_AGRS);
            return;
        }
        long startTime = System.currentTimeMillis();
        ComPareJar comPareJar = new ComPareJar();
        first_jar_path=args[0];
        second_jar_path=args[1];
        comPareJar.setFirst_jar_path(first_jar_path);
        comPareJar.setSecond_jar_path(second_jar_path);
        comPareJar.compare();
        long costTime = System.currentTimeMillis() - startTime;
//        Log.d("执行耗时："+costTime/1000);

    }

    private static boolean argsCheck(String[] args) {
        if (args.length == 2) {
            return true;
        }
        return false;
    }

}

