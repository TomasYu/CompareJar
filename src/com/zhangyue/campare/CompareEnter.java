package com.zhangyue.campare;



import com.zhangyue.campare.tools.Command;
import sun.tools.java.ClassFile;
import sun.tools.java.ClassPath;
import sun.tools.java.Identifier;
import sun.tools.java.Package;

import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zy1 on 15/11/2017.
 */
public class CompareEnter {
    public static void main(String[] args) {
        argsCheck();
        ComPareJar comPareJar = new ComPareJar();
        comPareJar.compare();


    }

    private static void argsCheck() {
    }

}

