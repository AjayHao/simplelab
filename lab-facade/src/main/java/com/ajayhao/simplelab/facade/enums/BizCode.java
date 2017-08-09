package com.ajayhao.simplelab.facade.enums;

import com.ajayhao.simplelab.facade.enums.AbstractBizCode;

/**
 * Created by AjayHao on 2017/7/25.
 */
public enum BizCode implements AbstractBizCode {
    SUCCESS("0", "业务处理成功", "COMMON"),
    FAIL("1", "业务处理失败", "COMMON"),
    INVALID_PARAM("2", "参数不合法", "COMMON"),
    SYS_ERROR("98", "系统异常", "COMMON"),
    UNKNOWN("99", "未知异常", "COMMON");

    private final String code;
    private final String message;
    private final String type;

    private BizCode(String code, String message, String type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }


    public static BizCode getBizCode(String code) {
        for(BizCode bizCode : values()) {
            if(bizCode.getCode().equals(code)) {
                return bizCode;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getType() {
        return type;
    }
}
