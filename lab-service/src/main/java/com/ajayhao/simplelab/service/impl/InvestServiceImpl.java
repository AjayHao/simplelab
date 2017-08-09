package com.ajayhao.simplelab.service.impl;


import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.dal.InvestDAO;
import com.ajayhao.simplelab.dal.entity.InvestInfoDO;
import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.facade.dto.InvestInfoDTO;
import com.ajayhao.simplelab.service.CommonParamService;
import com.ajayhao.simplelab.service.InvestService;
import com.ajayhao.simplelab.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by AjayHao on 2017/7/13.
 */
@Service("investService")
public class InvestServiceImpl implements InvestService {

    Logger log = LoggerFactory.getLogger(InvestServiceImpl.class);
    private BeanCopier toOutCopier = BeanCopier.create(InvestInfoDO.class, InvestInfoDTO.class,false);
    private BeanCopier fromOutCopier = BeanCopier.create(InvestInfoDTO.class, InvestInfoDO.class,false);

    private static final BigDecimal DAYS_OF_YEAR = new BigDecimal("365");
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100.00");

    @Autowired
    InvestDAO investDAO;

    @Autowired
    CommonParamService commonParamService;

    /*
    * 新增投资信息
    * */
    @Override
    public void addInvestInfo(InvestInfoDTO investInfoDTO) {
        InvestInfoDO investInfoDO = new InvestInfoDO();
        fromOutCopier.copy(investInfoDTO, investInfoDO , null);
        investDAO.insertInvestInfo(investInfoDO);
    }

    /*
    * 查询投资信息列表
    * */
    @Override
    public List<InvestInfoDTO> queryInvestInfoList(String[] periods) {
        Set<String> set = new HashSet<>();
        if(periods != null && periods.length != 0){
            for(String b : periods){
                set.add(b);
            }
        }

        List<InvestInfoDO> investInfoList = investDAO.queryInvestInfoList();
        List<InvestInfoDTO> retList = new ArrayList<>();
        for(InvestInfoDO investInfo : investInfoList){
            //判断是否返回
            String periodFlag = judgeExpiration(investInfo);
            if(set.contains(periodFlag)) {
                InvestInfoDTO investDTO = new InvestInfoDTO();
                toOutCopier.copy(investInfo, investDTO, null);
                investDTO.setAnnualYield(calculateAnnualYield(investDTO));
                try {
                    CommonParamDTO projectTypeDic = commonParamService.getParamByGroupAndCode("PROJECT_TYPE",investDTO.getProjectType());
                    CommonParamDTO mainChannelDic = commonParamService.getParamByGroupAndCode("MAIN_CHANNEL",investDTO.getMainChannel());
                    CommonParamDTO subChannelDic = commonParamService.getParamByGroupAndCode("SUB_CHANNEL",investDTO.getSubChannel());
                    investDTO.setProjectTypeDe(projectTypeDic.getParamValue());
                    investDTO.setMainChannelDe(mainChannelDic.getParamValue());
                    investDTO.setSubChannelDe(subChannelDic.getParamValue());
                } catch (BaseException e) {
                    e.printStackTrace();
                }
                retList.add(investDTO);
            }
        }
        return retList;
    }

    /*
    * 查询投资信息 by id
    * */
    @Override
    public InvestInfoDTO queryInvestInfo(String id) {
        InvestInfoDO investInfoDO = investDAO.queryInvestInfo(id);
        InvestInfoDTO investInfoDTO = new InvestInfoDTO();
        toOutCopier.copy(investInfoDO, investInfoDTO, null);
        investInfoDTO.setAnnualYield(calculateAnnualYield(investInfoDTO));
        return investInfoDTO;
    }


    /*
    * 计算年化收益率
    * */
    @Override
    public BigDecimal calculateAnnualYield(InvestInfoDTO investInfo) {
        String beginDate = investInfo.getBeginDate();
        String endDate = investInfo.getEndDate();
        BigDecimal cost = investInfo.getCost();
        BigDecimal income = investInfo.getIncome();
        if (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate))
            return null;

        if (cost == null || cost.compareTo(BigDecimal.ZERO) == 0)
            return null;

        if (income == null)
            return null;

        BigDecimal daysInterval =
                new BigDecimal(DateUtils.days(DateUtils.parseToDate(beginDate, "yyyyMMdd"),
                                    DateUtils.parseToDate(endDate, "yyyyMMdd"))+1);

        BigDecimal b = income.divide(cost, 8, RoundingMode.HALF_DOWN);
        b = b.multiply(DAYS_OF_YEAR).multiply(ONE_HUNDRED).divide(daysInterval, 2 , BigDecimal.ROUND_HALF_DOWN);
        return b;
    }

    /*
    * 删除投资信息
    * */
    @Override
    public void removeInvestInfo(InvestInfoDTO investInfoDTO) {
        InvestInfoDO investInfoDO = new InvestInfoDO();
        fromOutCopier.copy(investInfoDTO, investInfoDO , null);
        investDAO.insertInvestInfo(investInfoDO);
    }


    private String judgeExpiration(InvestInfoDO investInfo) {
        String endDate = investInfo.getEndDate();
        if(org.apache.commons.lang3.StringUtils.isEmpty(endDate)){
            return "2";
        }else{
            Date now = new Date();
            if(DateUtils.days(now, DateUtils.yyyymmdd(endDate)) > 0){
                return "2";
            }else{
                return "1";
            }
        }
    }
}
