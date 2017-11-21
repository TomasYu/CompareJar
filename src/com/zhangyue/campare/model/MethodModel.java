package com.zhangyue.campare.model;

import java.util.List;

/**
 * Created by zy1 on 16/11/2017.
 */
public class MethodModel {
    private String mName;
    private String mReturnType;
    private List<String> mArgs;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmReturnType() {
        return mReturnType;
    }

    public void setmReturnType(String mReturnType) {
        this.mReturnType = mReturnType;
    }

    public List<String> getmArgs() {
        return mArgs;
    }

    public void setmArgs(List<String> mArgs) {
        this.mArgs = mArgs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MethodModel)) return false;

        MethodModel that = (MethodModel) o;

        if (getmName() != null ? !getmName().equals(that.getmName()) : that.getmName() != null) return false;
        if (getmReturnType() != null ? !getmReturnType().equals(that.getmReturnType()) : that.getmReturnType() != null)
            return false;
        return getmArgs() != null ? getmArgs().equals(that.getmArgs()) : that.getmArgs() == null;
    }

    @Override
    public int hashCode() {
        int result = getmName().hashCode();
        result = 31 * result + getmReturnType().hashCode();
        result = 31 * result + (getmArgs() != null ? getmArgs().hashCode() : 0);
        return result;
    }
}
