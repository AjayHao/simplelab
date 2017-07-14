package com.ajayhao.simplelab.dal;

import com.ajayhao.simplelab.dal.entity.InvestInfo;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public interface InvestDAO {
    List<InvestInfo> queryInvestInfoList();

    InvestInfo queryInvestInfo(String testId);

    int insertInvestInfo(InvestInfo testInfo);
}
