package com.zhangyue.campare;

import com.zhangyue.campare.tools.Log;

/**
 * Created by zy1 on 15/11/2017.
 */
public class CompareEnter {
    static String first_jar_path = "C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin.jar";
    static String second_jar_path = "C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin2.jar";

    public static void main(String[] args) {
        if (!argsCheck(args)) {
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
        Log.d(costTime/1000+"");

    }

    private static boolean argsCheck(String[] args) {
        if (args.length == 2) {
            return true;
        }
        return false;
    }

}

