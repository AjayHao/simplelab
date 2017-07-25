package com.ajayhao.simplelab.base.enums;

import com.ajayhao.simplelab.base.dto.AbstractBizCode;

/**
 * Created by AjayHao on 2017/7/25.
 */
public enum BizCode implements AbstractBizCode {
    SUCCESS("0", "业务成功", "COMMON"),
    FAIL("1", "业务处理失败", "COMMON"),
    SYS_ERROR("2", "系统异常", "COMMON");

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
