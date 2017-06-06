package com.ajayhao.simplelab.base;

import com.ajayhao.core.base.AbstractResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Administrator on 2016/5/29 0029.
 */
public class BaseResponse extends AbstractResponse{
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}