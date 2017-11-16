package com.zhangyue.campare.tools;

import com.zhangyue.campare.tools.Command;

/**
 * Created by zy1 on 16/11/2017.
 */
public class JavaCommand {
    public static final String STRING_EXTRACT_JAR ="jar -xf ";
    public static final String STRING_JAVAP_CLASS ="javap -p ";

    public static final void extractJar(String jarPath){
        String result = Command.exeCmd(STRING_EXTRACT_JAR.concat(jarPath));
        System.out.println(result);
    }

    public static String javapClass(String classFilePath){
        String result = Command.exeCmd(STRING_JAVAP_CLASS.concat(classFilePath));
        return result;
    }
}
