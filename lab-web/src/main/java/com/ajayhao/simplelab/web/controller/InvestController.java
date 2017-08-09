package com.ajayhao.simplelab.web.controller;


import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.dal.entity.InvestInfoDO;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.facade.dto.InvestInfoDTO;
import com.ajayhao.simplelab.facade.dto.response.InvestResponse;
import com.ajayhao.simplelab.facade.enums.BizCode;
import com.ajayhao.simplelab.service.CommonParamService;
import com.ajayhao.simplelab.service.InvestService;
import com.ajayhao.simplelab.util.DateUtils;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

}
