package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.cl.CommonParamCache;
import com.ajayhao.simplelab.dal.CommonParamDAO;
import com.ajayhao.simplelab.dal.entity.CommonParamDO;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.service.CommonParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AjayHao on 2017/8/7.
 */
@Service("commonParamService")
public class CommonParamServiceImpl implements CommonParamService {

    @Value("${cache.enabled}")
    private boolean cacheEnabled;

    @Autowired
    private CommonParamDAO commonParamDAO;

    @Autowired
    private CommonParamCache commonParamCache;

    @Override
    public CommonParamDTO getParamByGroupAndCode(String groupName, String paramCode) throws BaseException {
        CommonParamDTO paramDTO = null;
        if(cacheEnabled){
            String paramValue = commonParamCache.getByNameKey(groupName, paramCode);
            if(StringUtils.isBlank(paramValue)){
                List<CommonParamDO> paramList = commonParamDAO.queryByGroup(groupName);
                if(CollectionUtils.isEmpty(paramList)){
                    throw new BaseException("参数配置表中groupName为{}的参数项未配置", groupName);
                }
                commonParamCache.initData(groupName, transferDtoList(paramList));
                paramValue = commonParamCache.getByNameKey(groupName, paramCode);
            }
            paramDTO =  new CommonParamDTO(groupName, paramCode, paramValue);
        }else{
            CommonParamDO paramDO = commonParamDAO.queryByGroupAndCode(groupName, paramCode);
            paramDTO = transferDto(paramDO);
        }
        return paramDTO;
    }

    private List<CommonParamDTO> transferDtoList(List<CommonParamDO> doList){
        List<CommonParamDTO> dtoList = new ArrayList<>();
        for(CommonParamDO _do : doList){
            CommonParamDTO _dto = transferDto(_do);
            dtoList.add(_dto);
        }
        return dtoList;
    }

    private CommonParamDTO transferDto(CommonParamDO _do){
        CommonParamDTO _dto = new CommonParamDTO();
        _dto.setParamGroup(_do.getParamGroup());
        _dto.setParamCode(_do.getParamCode());
        _dto.setParamValue(_do.getParamValue());
        return _dto;
    }

}
