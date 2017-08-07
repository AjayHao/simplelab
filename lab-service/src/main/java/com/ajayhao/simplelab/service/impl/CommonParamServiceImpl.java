package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.cl.CommonParamCache;
import com.ajayhao.simplelab.dal.CommonParamDAO;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.service.CommonParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by AjayHao on 2017/8/7.
 */
public class CommonParamServiceImpl implements CommonParamService {

    @Value("${redis.enabled}")
    private String cacheEnabled;

    @Autowired
    private CommonParamDAO commonParamDAO;

    @Autowired
    private CommonParamCache commonParamCache;

    @Override
    public List<CommonParamDTO> getParams() {
        return null;
    }

    @Override
    public List<CommonParamDTO> getParamByGroupName(String groupName) {
        return null;
    }

    @Override
    public CommonParamDTO getParamByGroupAndParam(String groupName, String paramName) {
        return null;
    }
}
