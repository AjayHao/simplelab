package com.ajayhao.simplelab.facade.dto;

import com.ajayhao.simplelab.facade.dto.common.BaseDTO;

import java.io.Serializable;

/**
 * Created by AjayHao on 2017/8/7.
 */
public class CommonParamDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 4467044355295269210L;

    public CommonParamDTO(){}

    public CommonParamDTO(String paramGroup, String paramCode, String paramValue){
        this.paramGroup = paramGroup;
        this.paramCode = paramCode;
        this.paramValue = paramValue;
    }

    private String paramGroup;

    private String paramCode;

    private String paramValue;

    private String paramDesc;

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
