package com.ajayhao.simplelab.base.exception;

import com.ajayhao.simplelab.base.exception.AbstractException;
import com.ajayhao.simplelab.facade.enums.BizCode;

import java.text.MessageFormat;

/**
 * Created by AjayHao on 2017/7/26.
 */
public class BaseException extends AbstractException {

    public BaseException(String msg, String ... params){
        super(msg, params);
    }

    public BaseException(String msg, Throwable cause, String ... params) {
        super(msg, cause, params);
    }

    public BaseException(BizCode bizCode, String msg, String ... params){
        super(MessageFormat.format("系统异常：{}" + msg, bizCode.getMessage(), params));
    }

    public BaseException(BizCode bizCode, Throwable cause, String msg, String ... params) {
        super(MessageFormat.format("系统异常：{}" + msg, bizCode.getMessage(), params), cause);
    }

}
