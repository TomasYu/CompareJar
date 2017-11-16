package com.zhangyue.campare;

import com.sun.tools.corba.se.idl.Util;
import com.zhangyue.campare.model.ClassModel;
import com.zhangyue.campare.model.MethodModel;
import com.zhangyue.campare.tools.JavaCommand;
import com.zhangyue.campare.tools.Log;
import com.zhangyue.campare.tools.Utils;
import com.zhangyue.campare.tools.Zip;

import java.util.*;

/**
 * Created by zy1 on 16/11/2017.
 */
public class ComPareJar {
    String first_jar_path = "C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin.jar";
    String second_jar_path = "C:\\Users\\zy1\\Downloads\\japi-compliance-checker-master\\iReader_plugin2.jar";
    public static final String unZipFileDir="./temp";
    public ComPareJar(){

    }

    public void compare(){

        Map<String, ClassModel> map1 = readJarFile(first_jar_path);

        Map<String, ClassModel> map2 = readJarFile(second_jar_path);

        Iterator<Map.Entry<String, ClassModel>> iterator = map2.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, ClassModel> next = iterator.next();
            String key = next.getKey();
            if (map1.containsKey(key)){
                ClassModel classModel = map1.get(key);
                ClassModel classModel2 = map2.get(key);
                if (!classModel.equals(classModel2) ){
                    Log.d(classModel.toString());
//                    Log.d(classModel2.toString());
                }
            }else {
                //新增了类
                Log.d("增加了类："+map2.get(key).getmClassName());
            }
        }

        int a = 50;
    }

    private Map<String,ClassModel> readJarFile(String jarFilePath) {
        Zip zip = new Zip();
        zip.unzip(jarFilePath,unZipFileDir,true);
        Map<String,ClassModel> classModelMap= new HashMap<>();
        for (String mZipFile : zip.mZipFiles) {
            //内部类先不管
            if (mZipFile.contains("$")) {
                continue;
            }
            String string = JavaCommand.javapClass(mZipFile);
            if (Utils.isEmpty(string)) {
                continue;
            }
            //去掉  Compiled from "CONSTANT.java 这一行
            String[] oldStrArr = string.split("\n");
            String[] newStrArr = new String[oldStrArr.length-1];
            System.arraycopy(oldStrArr,1,newStrArr,0,oldStrArr.length-1);
            //解析classmodel
            ClassModel classModel = analyzeClassString(newStrArr);
            classModelMap.put(classModel.getmClassName(),classModel);
        }
        return classModelMap;
    }

    private ClassModel analyzeClassString(String[] strArr) {
        ClassModel classModel = new ClassModel();
        List<MethodModel> methodList = new ArrayList<>();
        List<String> filedList = new ArrayList<>();
        for (int i = 0; i < strArr.length; i++) {
            String temp = strArr[i].replaceAll(";","");
            temp = temp.replaceAll("}","");
            //第一行是类名
            if (i==0) {
                classModel.setmClassName(temp.replace("{",""));
                continue;
            }

            //方法
            if (temp.contains("()")) {
                MethodModel methodModel = anaylzeMethodString(temp);
                methodList.add(methodModel);
            }
            if (!Utils.isEmpty(temp)) {
                filedList.add(temp);
            }
        }
        classModel.setmFiled(filedList);
        classModel.setmMethod(methodList);
        return classModel;
    }

    private MethodModel anaylzeMethodString(String methodString) {
//        private void initDialogProgress();
        MethodModel methodModel = new MethodModel();
        //得到参数
        String argString = methodString.substring(methodString.indexOf("(")+1, methodString.indexOf(")"));
        if (!Utils.isEmpty(argString)) {
            List<String> args =Arrays.asList(argString.split(","));
            methodModel.setmArgs(args);
        }

        //得到方法名
        String[] split = methodString.split(" ");
        String methodName=split[split.length-1];
        methodName = methodName.substring(0,methodName.indexOf("("));
        methodModel.setmName(methodName);

        //得到返回类型
        //如果方法名中包含. 那么就是构造方法 返回值就给方法名即可
        if (methodName.contains(".")) {
            methodModel.setmReturnType(methodName);
        }else{
            methodModel.setmReturnType(split[split.length-2]);
        }
        return methodModel;
    }

}
