package com.ajayhao.simplelab.base;

import com.ajayhao.core.base.AbstractRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by Ajay Hao on 2016/5/29 0029.
 */
public class BaseRequest implements Serializable{
    private static final long serialVersionUID = 5105222290214257880L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

