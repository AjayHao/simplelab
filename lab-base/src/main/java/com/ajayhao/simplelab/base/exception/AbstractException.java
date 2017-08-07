package com.ajayhao.simplelab.base.exception;

import org.slf4j.helpers.MessageFormatter;

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
