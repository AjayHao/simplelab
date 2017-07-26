package com.ajayhao.simplelab.dal;

import com.ajayhao.simplelab.base.BaseException;
import com.ajayhao.simplelab.dal.entity.CommonParamDO;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/26.
 */
public interface CommonParamDAO {

    List<CommonParamDO> queryAll();

    List<CommonParamDO> queryByGroup(String paramGroup);

    CommonParamDO queryByGroupAndName(String paramGroup, String paramName) throws BaseException;
}
