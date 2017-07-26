package com.ajayhao.simplelab.dal.impl;

import com.ajayhao.simplelab.base.BaseException;
import com.ajayhao.simplelab.base.exception.AbstractException;
import com.ajayhao.simplelab.dal.CommonParamDAO;
import com.ajayhao.simplelab.dal.entity.CommonParamDO;
import com.ajayhao.simplelab.dal.mapper.CommonParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/26.
 */
@Repository("commonParamDAO")
public class CommonParamDAOImpl implements CommonParamDAO{

    @Autowired
    private CommonParamMapper commonParamMapper;

    @Override
    public List<CommonParamDO> queryAll() {
        return commonParamMapper.queryParamList(null, null);
    }

    @Override
    public List<CommonParamDO> queryByGroup(String paramGroup) {
        return commonParamMapper.queryParamList(paramGroup, null);
    }

    @Override
    public CommonParamDO queryByGroupAndName(String paramGroup, String paramName) throws BaseException {
        List<CommonParamDO> list = commonParamMapper.queryParamList(paramGroup, paramName);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else if(list.size() == 1){
            return list.get(0);
        }else{
            throw new BaseException("配置有错误，存在重复的参数配置项-组名：{}，变量名：{}",paramGroup, paramName);
        }
    }
}
