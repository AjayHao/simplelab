package com.ajayhao.simplelab.service;

import com.ajayhao.simplelab.dal.entity.InvestInfoDO;
import com.ajayhao.simplelab.facade.dto.InvestInfoDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public interface InvestService {

    void addInvestInfo(InvestInfoDTO investDto);

    /*
    * 查询投资信息列表
    * */
    List<InvestInfoDTO> queryInvestInfoList(String[] periods);

    InvestInfoDTO queryInvestInfo(String id);


    /*
    * 计算年化收益率
    * */
    BigDecimal calculateAnnualYield(InvestInfoDTO investInfoDTO);

    /*
    * 删除投资信息
    * */
    void removeInvestInfo(String id);

    /*
    * 修改投资信息
    * */
    void modifyInvestInfo(InvestInfoDTO investInfoDTO);
}
