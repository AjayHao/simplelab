package com.ajayhao.simplelab.dal.entity;

/**
 * Created by haozhenjie on 2017/7/26.
 * <p>
 * 公共参数
 */
public class CommonParamDO extends BaseDO{

    /**
     * 参数组
     */
    private String paramGroup;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数描述
     */
    private String paramDesc;

    /**
     * Gets param group.
     *
     * @return the param group
     */
    public String getParamGroup() {
        return paramGroup;
    }

    /**
     * Sets param group.
     *
     * @param paramGroup the param group
     */
    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    /**
     * Gets param code.
     *
     * @return the param code
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * Sets param code.
     *
     * @param paramCode the param code
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    /**
     * Gets param value.
     *
     * @return the param value
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Sets param value.
     *
     * @param paramValue the param value
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * Gets param desc.
     *
     * @return the param desc
     */
    public String getParamDesc() {
        return paramDesc;
    }

    /**
     * Sets param desc.
     *
     * @param paramDesc the param desc
     */
    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
