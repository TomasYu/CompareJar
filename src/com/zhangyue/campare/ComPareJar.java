package com.zhangyue.campare;

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
    private  boolean mIgnoreFile = false;   //是否忽略字段的变化
    private  boolean mIgnoreMethod = false; //是否忽略方法的变化

    private int mAddFieldCount =  0;
    private int mRemoveFieldCount =  0;
    private int mAddMethodCount = 0;
    private int mRemoveMethodCount = 0;
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
                    compareModel(classModel,classModel2);
                }
            }else {
                //新增了类
                Log.d("增加了类："+map2.get(key).getmClassName());
            }
        }
        Log.d("总结：");
        Log.d("增加方法总数："+mAddMethodCount);
        Log.d("删除方法总数："+mRemoveMethodCount);
        Log.d("增加字段总数："+mAddFieldCount);
        Log.d("删除字段总数："+mRemoveFieldCount);
    }

    private void compareModel(ClassModel mod1,ClassModel mod2) {

        Log.d(mod1.getmClassName()+"的差异");

        //对比字段
        List<String> filed1 = mod1.getmFiled();
        List<String> filed2 = mod2.getmFiled();
        if (filed1 != filed2 && !mIgnoreFile) {
            for (String field : filed1) {
                if (filed2.contains(field)) {
                    filed2.remove(field);
                    continue;
                }
                Log.d("filed2 删除了字段"+field);
                mRemoveFieldCount++;

            }

            for (String field : filed2) {
                if (filed1.contains(field)){
                    continue;
                }
                Log.d("file 2 增加了字段"+field);
                mAddFieldCount++;
            }
        }

        //对比方法

        List<MethodModel> methodMod1 = mod1.getmMethod();
        List<MethodModel> methodMod2 = mod2.getmMethod();
        if (methodMod1 != methodMod2 && !mIgnoreMethod) {
            for (MethodModel method : methodMod1) {
                if (methodMod2.contains(method)) {
                    methodMod2.remove(method);
                    continue;
                }
                Log.d("filed2 删除了方法 "+method.getmName());
                mRemoveMethodCount++;
            }

            for (MethodModel method : methodMod2) {
                if (methodMod1.contains(method)){
                    continue;
                }
                Log.d("file 2 增加了方法 "+method.getmName());
                mAddMethodCount++;
            }
        }
        Log.d("-------------------------------------------");

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
            if (temp.contains("(") && temp.contains(")") && !mIgnoreMethod) {
                MethodModel methodModel = anaylzeMethodString(temp);
                if (methodModel != null) {
                    methodList.add(methodModel);
                }
                continue;
            }
            //字段
            if (!Utils.isEmpty(temp) && !mIgnoreFile) {
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

        //得到参数之后  去掉括号内的参数信息
        String temp = methodString.substring(0, methodString.indexOf("("));
        //得到方法名
        //public void onActivityResult(int, int, android.content.Intent)
        String[] split = temp.split(" ");
        // 减一是因为第一个是方法名
        String methodName=split[split.length-1];
        //此时，methodName是方法名，不包含后面的括号和参数
        if (methodName.contains("$")) {
            return null;
        }
        methodName = methodString.substring(methodString.indexOf(methodName));
        methodModel.setmName(methodName);

        //得到返回类型
        //如果方法名中包含. 那么就是构造方法 返回值就给方法名即可
        if (methodName.contains(".")) {
            methodModel.setmReturnType(methodName);
        }else{
            // -2 是因为倒数第二个是返回类型
            methodModel.setmReturnType(split[split.length-2]);
        }
        return methodModel;
    }

    public void setmIgnoreFile(boolean mIgnoreFile) {
        this.mIgnoreFile = mIgnoreFile;
    }

    public void setmIgnoreMethod(boolean mIgnoreMethod) {
        this.mIgnoreMethod = mIgnoreMethod;
    }
}
