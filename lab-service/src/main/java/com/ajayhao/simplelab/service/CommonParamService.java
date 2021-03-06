package com.ajayhao.simplelab.service;

import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;

import java.util.List;

/**
 * Created by AjayHao on 2017/8/7.
 */
public interface CommonParamService {

    List<CommonParamDTO> getParamsByGroup(String groupName) throws BaseException;

    CommonParamDTO getParamByGroupAndCode(String groupName, String paramCode) throws BaseException;

}
