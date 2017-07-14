package com.ajayhao.simplelab.web.controller;

import com.ajayhao.core.enums.BizCode;
import com.ajayhao.simplelab.dal.entity.InvestInfo;
import com.ajayhao.simplelab.service.InvestService;
import com.ajayhao.simplelab.web.dto.InvestDTO;
import com.ajayhao.simplelab.web.dto.response.InvestResponse;
import net.sf.cglib.beans.BeanCopier;
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
    private BeanCopier toOutCopier = BeanCopier.create(InvestInfo.class, InvestDTO.class,false);
    private BeanCopier fromOutCopier = BeanCopier.create(InvestDTO.class, InvestInfo.class,false);

    @Autowired
    private InvestService investService;

    @RequestMapping(path="/{id}", method = {RequestMethod.GET})
    public InvestResponse getInvestInfoById(@RequestParam String id) {
        InvestResponse response = new InvestResponse();
        InvestInfo investInfo = investService.queryInvestInfo(id);
        List<InvestDTO> retList = new ArrayList<>();
        InvestDTO investDTO = new InvestDTO();
        toOutCopier.copy(investInfo, investDTO, null);
        retList.add(investDTO);
        response.setRetCode(BizCode.Success.code());
        response.setRetMsg("查询成功");
        response.setInvestDTOs(retList);
        return response;
    }

    @RequestMapping(path="/list", method = {RequestMethod.GET})
    public InvestResponse getInvestInfoList() {
        InvestResponse response = new InvestResponse();
        List<InvestInfo> investInfoList = investService.queryInvestInfoList();
        List<InvestDTO> retList = new ArrayList<>();
        for(InvestInfo investInfo : investInfoList){
            InvestDTO investDTO = new InvestDTO();
            toOutCopier.copy(investInfo, investDTO, null);
            retList.add(investDTO);
        }

        response.setRetCode(BizCode.Success.code());
        response.setRetMsg("查询成功");
        response.setInvestDTOs(retList);
        return response;
    }
}
