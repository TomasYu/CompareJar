package com.zhangyue.campare;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.zhangyue.campare.model.ClassInfo;
import com.zhangyue.campare.model.ClassModel;
import com.zhangyue.campare.model.MethodModel;
import com.zhangyue.campare.tools.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by zy1 on 16/11/2017.
 */
public class ComPareJar {
    private String first_jar_path = "";     //第一个jar的路径
    private String second_jar_path = "";    //第二个jar的路径
    public static final String unZipFileDir=Utils.getCurPath()+"\\temp"; //解压的缓存目录
    private  boolean mIgnoreFile = false;   //是否忽略字段的变化
    private  boolean mIgnoreMethod = false; //是否忽略方法的变化

    private int mAddFieldCount =  0;            //计数用
    private int mRemoveFieldCount =  0;         //计数用
    private int mAddMethodCount = 0;            //计数用
    private int mRemoveMethodCount = 0;         //计数用
    private int mAddClassCount = 0;             //计数用
    private int mRemoveClassCount = 0;          //计数用
    private StringBuffer sbResult;              //计数用
    private ArrayList<String> mResultList = new ArrayList<>(10);// 用来记录结果，最后输出
    public ComPareJar(){

    }

    public void compare(){
        //清理工作空间
        clearTemp();

        Map<String, ClassInfo> map1 = readJarFileMd5(first_jar_path,unZipFileDir+"/1");     //读取第一个jar包的md5
        Map<String, ClassInfo> map2 = readJarFileMd5(second_jar_path,unZipFileDir+"/2");    //读取第二个jar包的md5

        List<String> mDiffFileKey = getDiffClass(map1, map2);   //得到md5不一样的class

        for (String key : mDiffFileKey) {
            //从不同的mDiffFileKey 取出两个class
            //1
            ClassInfo classInfo1 = map1.get(key);
            ClassModel classMod1 = readClassModelFromFile(classInfo1.getmClassPath());//从class文件里面读取ClassMode
            //2
            ClassInfo classInfo2 = map2.get(key);
            ClassModel classMod2 = readClassModelFromFile(classInfo2.getmClassPath());//从class文件里面读取ClassMode

            compareModel(classMod1,classMod2);  //比较两个classModel
        }

        analyzeResult();//分析结果

        writeResult();// 写文件
    }

    /**
     * 得到ma5不同的class
     * @param map1
     * @param map2
     * @return
     */
    private List<String> getDiffClass(Map<String, ClassInfo> map1, Map<String, ClassInfo> map2) {
        List<String> mDiffFileKey = new ArrayList<>();
        sbResult = new StringBuffer();
        StringBuffer diffClass = new StringBuffer();
        Iterator<Map.Entry<String, ClassInfo>> iterator2 = map2.entrySet().iterator();
        while (iterator2.hasNext()){
            Map.Entry<String, ClassInfo> next = iterator2.next();
            String key = next.getKey();
            if (map1.containsKey(key)){
                ClassInfo classInfo1 = map1.get(key);
                ClassInfo classInfo2 = map2.get(key);
                if (!classInfo1.equals(classInfo2) ){
                    mDiffFileKey.add(key);
                }else {
                    map1.remove(key);
                }

            }else {
                //新增了类
                mAddClassCount++;
//                Log.d(Constant.NEW_JAR +" 增加了类："+key);
                diffClass.append(Constant.NEW_JAR +" 增加了类："+key);
                diffClass.append("\r\n");
            }
        }

        Iterator<Map.Entry<String, ClassInfo>> iterator1 = map1.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, ClassInfo> next = iterator1.next();
            String key = next.getKey();
            if (!map2.containsKey(key)) {
                //删除了类
                mRemoveClassCount++;
//                Log.d(Constant.NEW_JAR +" 删除了类："+key);
                diffClass.append(Constant.NEW_JAR +" 删除了类："+key);
                diffClass.append("\r\n");
            }
        }
        mResultList.add(diffClass.toString());
        return mDiffFileKey;
    }

    private void compareModel(ClassModel mod1,ClassModel mod2) {
        if (mod1 ==null || mod2 ==null) {
            return;
        }

        if (mod1.equals(mod2)) {
            return;
        }

        StringBuffer diff = new StringBuffer(); //一条记录

//        Log.d(mod1.getmClassName()+"的差异");
        diff.append(mod1.getmClassName()+"的差异");
        diff.append("\r\n");

        //对比字段
        List<String> filed1 = mod1.getmFiled();
        List<String> filed2 = mod2.getmFiled();
        if (!filed1.equals(filed2)  && !mIgnoreFile) {
            for (String field : filed1) {
                if (filed2.contains(field)) {
                    filed2.remove(field);
                    continue;
                }
//                Log.d(Constant.NEW_JAR +" 删除了字段"+field);
                diff.append(Constant.NEW_JAR +" 删除了字段"+field);
                diff.append("\r\n");
                mRemoveFieldCount++;

            }

            for (String field : filed2) {
                if (filed1.contains(field)){
                    continue;
                }
//                Log.d(Constant.NEW_JAR +" 增加了字段"+field);
                diff.append(Constant.NEW_JAR +" 增加了字段"+field);
                diff.append("\r\n");
                mAddFieldCount++;
            }
        }

        //对比方法

        List<MethodModel> methodMod1 = mod1.getmMethod();
        List<MethodModel> methodMod2 = mod2.getmMethod();
        if (!methodMod1.equals(methodMod2) && !mIgnoreMethod) {
            for (MethodModel method : methodMod1) {
                if (methodMod2.contains(method)) {
                    methodMod2.remove(method);
                    continue;
                }
//                Log.d(Constant.NEW_JAR +" 删除了方法 "+method.getmName());
                diff.append(Constant.NEW_JAR +" 删除了方法 "+method.getmName());
                diff.append("\r\n");
                mRemoveMethodCount++;
            }

            for (MethodModel method : methodMod2) {
                if (methodMod1.contains(method)){
                    continue;
                }
//                Log.d(Constant.NEW_JAR +" 增加了方法 "+method.getmName());
                diff.append(Constant.NEW_JAR +" 增加了方法 "+method.getmName());
                diff.append("\r\n");
                mAddMethodCount++;
            }
        }
//        Log.d(Constant.LINE_SPLITE);
        diff.append(Constant.LINE_SPLITE);
        diff.append("\r\n");
        mResultList.add(diff.toString());
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

        }
        return classModelMap;
    }

    /**
     * 删除缓存文件
     */
    private void clearTemp() {
        try {
            Utils.deleteIfExists(Paths.get(unZipFileDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Command.exeCmd("rm -rf "+unZipFileDir);
    }

    /**
     * 读取jar里面的所有的文件  以及md5
     * @param jarFilePath
     * @param unZipPath
     * @return
     */
    public Map<String,ClassInfo> readJarFileMd5(String jarFilePath, String unZipPath){
        Map<String,ClassInfo> jarMap = new HashMap<>();
        Zip zip = new Zip();
        zip.unzip(jarFilePath,unZipPath,true);
        for (String mZipFile : zip.mZipFiles) {
            //如果是内部类 包含$ 则不管
            if (mZipFile.contains("$")) {
                continue;
            }
            String key = mZipFile.substring(mZipFile.indexOf(unZipPath) + unZipPath.length() + 1);
            jarMap.put(key, new ClassInfo(mZipFile,MD5.getMd5ByFile(new File(mZipFile))));
        }
        return jarMap;
    }

    /**
     * 从class文件中读取ClassModel
     * @param classFilePath
     * @return
     */
    private ClassModel readClassModelFromFile(String classFilePath){
        String string = JavaCommand.javapClass(classFilePath);
        if (Utils.isEmpty(string)) {
            return null;
        }

        ClassModel classModel = analyzeClassString(string);
        return classModel;
    }

    /**
     * 根据字符数组解析ClassModel
     * @param classStr
     * @return
     */
    private ClassModel analyzeClassString(String classStr) {
        //去掉  Compiled from "CONSTANT.java 这一行
        String[] oldStrArr = classStr.split("\n");
        String[] newStrArr = new String[oldStrArr.length-1];
        System.arraycopy(oldStrArr,1,newStrArr,0,oldStrArr.length-1);
        //解析classmodel
        ClassModel classModel = new ClassModel();
        List<MethodModel> methodList = new ArrayList<>();
        List<String> filedList = new ArrayList<>();
        for (int i = 0; i < newStrArr.length; i++) {
            String temp = newStrArr[i].replaceAll(";","");
            temp = temp.replaceAll("}","");
            //第一行是类名
            if (i==0) {
                classModel.setmClassName(temp.replace("{",""));
                continue;
            }

            //方法
            if (temp.contains("(") && temp.contains(")") && !mIgnoreMethod) {
                MethodModel methodModel = anaylzeMethodStringV2(temp);
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

    /**
     * 解析方法
     * @param methodString
     * @return
     */
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

    /**
     * 解析方法
     * 这个版本的解析方法很粗暴， 直接设置一个名字 不去解析返回类型，参数
     * @param methodString
     * @return
     */
    private MethodModel anaylzeMethodStringV2(String methodString) {
        MethodModel methodModel = new MethodModel();
        methodModel.setmName(methodString);
        return methodModel;
    }


    public void setmIgnoreFile(boolean mIgnoreFile) {
        this.mIgnoreFile = mIgnoreFile;
    }
    public void setmIgnoreMethod(boolean mIgnoreMethod) {
        this.mIgnoreMethod = mIgnoreMethod;
    }

    public void setFirst_jar_path(String first_jar_path) {
        this.first_jar_path = first_jar_path;
    }

    public void setSecond_jar_path(String second_jar_path) {
        this.second_jar_path = second_jar_path;
    }

    /**
     * 结果输出到文件
     */
    private void writeResult() {
        try {
            String resultFilePath = unZipFileDir + "/result.txt";
            Path path = Paths.get(resultFilePath);
            Files.deleteIfExists(path);
            Files.createFile(path);
            Utils.write2File(resultFilePath,sbResult.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分析结果 组织文字
     */
    private void analyzeResult() {
//        //打印分隔符 方便查看
//        Log.d(Constant.LINE_SPLITE);
//        sbResult.append(Constant.LINE_SPLITE);
//        sbResult.append("\r\n");


        if ( mAddClassCount ==0
             &&mRemoveClassCount ==0
             &&mAddMethodCount ==0
             &&mRemoveMethodCount ==0
             &&mAddFieldCount ==0
             &&mRemoveFieldCount ==0){
            //jar 包无差异
            Log.d( Constant.JAR_NO_CHANGE);
            sbResult.append(Constant.JAR_NO_CHANGE);
            sbResult.append("\r\n");

        }else {
            Log.d(Constant.JAR_HAVE_CHANGE);
            sbResult.append(Constant.JAR_HAVE_CHANGE);
            sbResult.append("\r\n");
        }
        if (mAddClassCount !=0) {
            Log.d("增加类总数："+mAddClassCount);
            sbResult.append("增加类总数："+mAddClassCount);
            sbResult.append("\r\n");
        }
        if (mRemoveClassCount !=0) {
            Log.d("删除类总数："+mRemoveClassCount);
            sbResult.append("删除类总数："+mRemoveClassCount);
            sbResult.append("\r\n");
        }

        if (mAddMethodCount !=0) {
            Log.d("增加方法总数："+mAddMethodCount);
            sbResult.append("增加方法总数："+mAddMethodCount);
            sbResult.append("\r\n");
        }

        if (mRemoveMethodCount !=0) {
            Log.d("删除方法总数："+mRemoveMethodCount);
            sbResult.append("删除方法总数："+mRemoveMethodCount);
            sbResult.append("\r\n");
        }

        if (mAddFieldCount !=0) {
            Log.d("增加字段总数："+mAddFieldCount);
            sbResult.append("增加字段总数："+mAddFieldCount);
            sbResult.append("\r\n");
        }

        if (mRemoveFieldCount !=0) {
            Log.d("删除字段总数："+mRemoveFieldCount);
            sbResult.append("删除字段总数："+mRemoveFieldCount);
            sbResult.append("\r\n");
        }

        //打印分隔符 方便查看
        Log.d(Constant.LINE_SPLITE);
        sbResult.append(Constant.LINE_SPLITE);
        sbResult.append("\r\n");
        //打印具体的差异
        for (String result : mResultList) {
            Log.d(result);
            sbResult.append(result);
        }
    }

}
