package com.ajayhao.simplelab.web.controller;


import com.ajayhao.simplelab.facade.dto.InvestInfoDTO;
import com.ajayhao.simplelab.facade.dto.response.InvestResponse;
import com.ajayhao.simplelab.facade.enums.BizCode;
import com.ajayhao.simplelab.service.InvestService;
import com.ajayhao.simplelab.util.ObjectUtils;
import com.ajayhao.simplelab.util.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
@RestController
@RequestMapping("/invest")
public class InvestController {

    private static Logger logger = LoggerFactory.getLogger(InvestController.class);

    @Autowired
    private InvestService investService;

    /*此处需要留意顺序，/list会与/{id}的匹配方式混淆，因此必须填入periods参数以区分*/
    @RequestMapping(path="/list" , method = {RequestMethod.GET})
    public InvestResponse getInvestInfoList(@RequestParam("periods") String[] periods) {

        InvestResponse response = new InvestResponse();
        try {
            List<InvestInfoDTO> retList = investService.queryInvestInfoList(periods);
            response.setInvestDTOs(retList);
        }catch (Exception e){
            response.setRespCode(BizCode.FAIL.getCode());
            response.setRespMsg(e.getMessage());
        }
        return response;
    }

    /*路径参数*/
    @RequestMapping(path = "/{id}", method = {RequestMethod.GET})
    public InvestResponse getInvestInfoByIdPath(@PathVariable String id) {
        return getInvestResponse(id);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.DELETE})
    public InvestResponse deleteInvestInfoByIdPath(@PathVariable String id) {
        InvestResponse response = new InvestResponse();
        if (isIdValid(id, response)) return response;
        try {
            investService.removeInvestInfo(id);
        }catch (Exception e){
            response.setRespCode(BizCode.FAIL.getCode());
            response.setRespMsg("Exception :" + e + " params="+id);
        }
        return response;
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT})
    public InvestResponse modifyInvestInfo(@PathVariable String id, @RequestBody String params) {
        InvestResponse response = new InvestResponse();
        if (isIdValid(id, response)) return response;
        if (isParamObjValid(params, response)) return response;
        InvestInfoDTO investInfoDTO;
        try{
            investInfoDTO = ObjectUtils.fromJson(params,InvestInfoDTO.class);
            investInfoDTO.setId(id);
            investService.modifyInvestInfo(investInfoDTO);
        }catch(Exception e){
            response.setRespCode(BizCode.SYS_ERROR.getCode());
            response.setRespMsg("Exception :" + e + " params="+params);
        }

        return response;
    }

    @RequestMapping(path = "/", method = {RequestMethod.POST})
    public InvestResponse insertInvestInfo(@RequestBody String params) {
        InvestResponse response = new InvestResponse();
        //if (isIdValid(id, response)) return response;
        if (isParamObjValid(params, response)) return response;
        InvestInfoDTO investInfoDTO;
        try{
            investInfoDTO = ObjectUtils.fromJson(params,InvestInfoDTO.class);
            investInfoDTO.setId(SystemUtils.objectId());
            investService.addInvestInfo(investInfoDTO);
        }catch(Exception e){
            response.setRespCode(BizCode.SYS_ERROR.getCode());
            response.setRespMsg("Exception :" + e + " params="+params);
        }

        return response;
    }

    /*url参数*/
    @RequestMapping(method = {RequestMethod.GET})
    public InvestResponse getInvestInfoByIdParam(@RequestParam String id) {
        return getInvestResponse(id);
    }

    private InvestResponse getInvestResponse(String id) {
        InvestResponse response = new InvestResponse();
        List<InvestInfoDTO> retList = new ArrayList<>();
        InvestInfoDTO investDTO = investService.queryInvestInfo(id);
        retList.add(investDTO);
        response.setInvestDTOs(retList);
        return response;
    }

    private boolean isIdValid(@PathVariable String id, InvestResponse response) {
        if(StringUtils.isBlank(id)){
            logger.warn(BizCode.INVALID_PARAM.getMessage());
            response.setRespCode(BizCode.INVALID_PARAM.getCode());
            response.setRespMsg("传入的待修改对象id为空");
            return true;
        }
        return false;
    }

    private boolean isParamObjValid(@RequestBody String params, InvestResponse response) {
        if(StringUtils.isBlank(params)){
            logger.warn(BizCode.INVALID_PARAM.getMessage());
            response.setRespCode(BizCode.INVALID_PARAM.getCode());
            response.setRespMsg("传入的待修改对象参数为空");
            return true;
        }
        return false;
    }
}
