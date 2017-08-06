package com.ajayhao.simplelab.service;

import dto.CommonParamDTO;

import java.util.List;

/**
 * Created by AjayHao on 2017/8/7.
 */
public interface CommonParamService {

    List<CommonParamDTO> getParams();

    List<CommonParamDTO> getParamByGroupName(String groupName);

    CommonParamDTO getParamByGroupAndParam(String groupName, String paramName);

}
