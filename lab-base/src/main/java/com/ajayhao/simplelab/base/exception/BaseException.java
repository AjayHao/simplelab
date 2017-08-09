package com.ajayhao.simplelab.base.exception;

import com.ajayhao.simplelab.facade.enums.BizCode;
import org.slf4j.helpers.MessageFormatter;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by AjayHao on 2017/7/26.
 */
public class BaseException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = 3391824968260177264L;

    public BaseException(){}

    public BaseException(String msg, Object ... params){
        super(MessageFormatter.arrayFormat(msg, params).getMessage());
    }

    public BaseException(String msg, Throwable cause, String ... params) {
        super(MessageFormatter.arrayFormat(msg, params).getMessage(), cause);
    }

    public BaseException(BizCode bizCode, String msg){
        this(MessageFormat.format("系统异常：{}" + msg, bizCode.getMessage()));
    }

    public BaseException(BizCode bizCode, String msg, String ... params){
        this(MessageFormat.format("系统异常：{}" + msg, bizCode.getMessage(), params));
    }

    public BaseException(BizCode bizCode, Throwable cause, String msg, String ... params) {
        this(MessageFormat.format("系统异常：{}" + msg, bizCode.getMessage(), params), cause);
    }
}
