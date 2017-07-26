package com.ajayhao.simplelab.dal.entity;

/**
 * Created by haozhenjie on 2017/7/26.
 *
 * 公共参数
 */
public class CommonParamDO extends BaseDO{

    private String paramGroup;

    private String paramName;

    private String paramValue;

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
