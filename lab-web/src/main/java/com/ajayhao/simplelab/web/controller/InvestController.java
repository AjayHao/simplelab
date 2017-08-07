package com.ajayhao.simplelab.web.controller;

import com.ajayhao.core.util.CoreDateUtils;
import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.dal.entity.InvestInfo;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.facade.dto.InvestDTO;
import com.ajayhao.simplelab.facade.dto.response.InvestResponse;
import com.ajayhao.simplelab.facade.enums.BizCode;
import com.ajayhao.simplelab.service.CommonParamService;
import com.ajayhao.simplelab.service.InvestService;
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
    private BeanCopier toOutCopier = BeanCopier.create(InvestInfo.class, InvestDTO.class,false);
    private BeanCopier fromOutCopier = BeanCopier.create(InvestDTO.class, InvestInfo.class,false);

    private static Logger logger = LoggerFactory.getLogger(InvestController.class);

    @Autowired
    private InvestService investService;

    @Autowired
    private CommonParamService commonParamService;

    /*此处需要留意顺序，/list会与/{id}的匹配方式混淆，因此必须填入periods参数以区分*/
    @RequestMapping(path="/list" , method = {RequestMethod.GET})
    public InvestResponse getInvestInfoList(@RequestParam("periods") String[] periods) {
        Set<String> set = new HashSet<>();
        if(periods != null && periods.length != 0){
            for(String b : periods){
                set.add(b);
            }
        }
        InvestResponse response = new InvestResponse();
        List<InvestInfo> investInfoList = investService.queryInvestInfoList();
        List<InvestDTO> retList = new ArrayList<>();
        for(InvestInfo investInfo : investInfoList){
            //判断是否返回
            String periodFlag = judgeExpiration(investInfo);
            if(set.contains(periodFlag)) {
                InvestDTO investDTO = new InvestDTO();
                investDTO.setAnnualYield(investService.calculateAnnualYield(investInfo));
                toOutCopier.copy(investInfo, investDTO, null);
                try {
                    CommonParamDTO paramDTO = commonParamService.getParamByGroupAndParam("aaa","bbb");
                    investDTO.setProjectTypeDes(paramDTO.getParamName());
                } catch (BaseException e) {
                    e.printStackTrace();
                }
                retList.add(investDTO);
            }
        }

        response.setRespCode(BizCode.SUCCESS.getCode());
        response.setRespMsg("查询成功");
        response.setInvestDTOs(retList);
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
        InvestInfo investInfo = investService.queryInvestInfo(id);
        List<InvestDTO> retList = new ArrayList<>();
        InvestDTO investDTO = new InvestDTO();
        toOutCopier.copy(investInfo, investDTO, null);
        investDTO.setAnnualYield(investService.calculateAnnualYield(investInfo));
        retList.add(investDTO);
        response.setRespCode(BizCode.SUCCESS.getCode());
        response.setRespMsg("查询成功");
        response.setInvestDTOs(retList);
        return response;
    }

    private String judgeExpiration(InvestInfo investInfo) {
        String endDate = investInfo.getEndDate();
        if(StringUtils.isEmpty(endDate)){
            return "2";
        }else{
            Date now = new Date();
            if(CoreDateUtils.days(now,CoreDateUtils.yyyymmdd(endDate)) > 0){
                return "2";
            }else{
                return "1";
            }
        }
    }
}
