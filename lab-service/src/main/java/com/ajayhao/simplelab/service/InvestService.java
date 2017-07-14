package com.ajayhao.simplelab.service;

import com.ajayhao.simplelab.dal.entity.InvestInfo;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public interface InvestService {

    void addInvestInfo(InvestInfo investInfo);

    List<InvestInfo> queryInvestInfoList();

    InvestInfo queryInvestInfo(String id);
}
