package com.ajayhao.simplelab.dal;

import com.ajayhao.simplelab.dal.entity.InvestInfoDO;
import com.ajayhao.simplelab.facade.dto.InvestInfoDTO;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public interface InvestDAO {
    List<InvestInfoDO> queryInvestInfoList();

    InvestInfoDO queryInvestInfo(String id);

    int insertInvestInfo(InvestInfoDO testInfo);

    int deleteInvestInfo(String id);

    int modifyInvestInfo(InvestInfoDO investInfoDO);
}
