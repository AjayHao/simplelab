package com.ajayhao.simplelab.base.exception;

import com.ajayhao.simplelab.base.enums.BizCode;
import org.slf4j.helpers.MessageFormatter;

import java.text.MessageFormat;

/**
 * Created by AjayHao on 2017/7/26.
 */
public abstract class AbstractException extends Exception{

    public AbstractException(String msg, Object ... params){
        super(MessageFormatter.arrayFormat(msg, params).getMessage());
    }

    public AbstractException(String msg, Throwable cause, String ... params) {
        super(MessageFormatter.arrayFormat(msg, params).getMessage(), cause);
    }

}
