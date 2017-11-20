package com.zhangyue.campare.model;

/**
 * Created by zy1 on 20/11/2017.
 */
public class ClassInfo {
    private String mClassPath;
    private String mCLassMd5;

    public ClassInfo(String mClassPath, String mCLassMd5) {
        this.mClassPath = mClassPath;
        this.mCLassMd5 = mCLassMd5;
    }

    public String getmClassPath() {
        return mClassPath;
    }

    public void setmClassPath(String mClassPath) {
        this.mClassPath = mClassPath;
    }

    public String getmCLassMd5() {
        return mCLassMd5;
    }

    public void setmCLassMd5(String mCLassMd5) {
        this.mCLassMd5 = mCLassMd5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassInfo)) return false;

        ClassInfo classInfo = (ClassInfo) o;

        return getmCLassMd5().equals(classInfo.getmCLassMd5());
    }

    @Override
    public int hashCode() {
        return getmCLassMd5().hashCode();
    }
}
