package com.ajayhao.simplelab.dal;

import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.dal.entity.CommonParamDO;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/26.
 */
public interface CommonParamDAO {

    List<CommonParamDO> queryAll();

    List<CommonParamDO> queryByGroup(String paramGroup);

    CommonParamDO queryByGroupAndCode(String paramGroup, String paramCode) throws BaseException;
}
