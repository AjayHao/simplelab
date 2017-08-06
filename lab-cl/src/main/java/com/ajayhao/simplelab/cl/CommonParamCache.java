package com.ajayhao.simplelab.cl;

import com.ajayhao.simplelab.base.dto.BaseDTO;
import dto.CommonParamDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AjayHao on 2017/8/7.
 */
public interface CommonParamCache{

    boolean isGroupExist(String groupName);

    long initData(String groupName, List<CommonParamDTO> paramList);

    String getByNameKey(String groupName, String paramName);
}
