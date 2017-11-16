package com.zhangyue.campare.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zy1 on 16/11/2017.
 */
public class ClassModel {
    private String mClassName;
    private List<String> mFiled;
    private List<MethodModel> mMethod;

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public List<String> getmFiled() {
        return mFiled;
    }

    public void setmFiled(List<String> mFiled) {
        this.mFiled = mFiled;
    }

    public List<MethodModel> getmMethod() {
        return mMethod;
    }

    public void setmMethod(List<MethodModel> mMethod) {
        this.mMethod = mMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassModel)) return false;

        ClassModel that = (ClassModel) o;

        if (!getmClassName().equals(that.getmClassName())) return false;
        if (getmFiled() != null ? !getmFiled().equals(that.getmFiled()) : that.getmFiled() != null) return false;
        return getmMethod() != null ? getmMethod().equals(that.getmMethod()) : that.getmMethod() == null;
    }

    @Override
    public int hashCode() {
        int result = getmClassName().hashCode();
        result = 31 * result + (getmFiled() != null ? getmFiled().hashCode() : 0);
        result = 31 * result + (getmMethod() != null ? getmMethod().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassModel{" +
                "mClassName='" + mClassName + '\'' +
                ", mFiled=" + mFiled +
                ", mMethod=" + mMethod +
                '}';
    }
}
