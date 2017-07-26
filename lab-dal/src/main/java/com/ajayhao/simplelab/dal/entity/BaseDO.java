package com.ajayhao.simplelab.dal.entity;

import com.ajayhao.simplelab.util.ObjectUtils;

/**
 * Created by haozhenjie on 2017/7/26.
 */
public class BaseDO {

    public BaseDO() {
    }

    @Override
    public String toString() {
        return ObjectUtils.toJson(this);
    }

}
