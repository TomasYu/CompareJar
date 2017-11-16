package com.zhangyue.campare;

import com.zhangyue.campare.tools.Log;

/**
 * Created by zy1 on 15/11/2017.
 */
public class CompareEnter {
    public static void main(String[] args) {
        argsCheck();
        long startTime = System.currentTimeMillis();
        ComPareJar comPareJar = new ComPareJar();
        comPareJar.compare();
        long costTime = System.currentTimeMillis() - startTime;
        Log.d(costTime/1000+"");

    }

    private static void argsCheck() {
    }

}

