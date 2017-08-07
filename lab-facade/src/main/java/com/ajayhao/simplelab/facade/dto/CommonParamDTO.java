package com.ajayhao.simplelab.facade.dto;

import com.ajayhao.simplelab.facade.dto.common.BaseDTO;

import java.io.Serializable;

/**
 * Created by AjayHao on 2017/8/7.
 */
public class CommonParamDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 4467044355295269210L;

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
