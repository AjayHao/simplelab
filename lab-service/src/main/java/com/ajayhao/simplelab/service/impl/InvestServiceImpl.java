package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.dal.InvestDAO;
import com.ajayhao.simplelab.dal.entity.InvestInfo;
import com.ajayhao.simplelab.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
@Service
public class InvestServiceImpl implements InvestService {

    @Autowired
    InvestDAO investDAO;

    @Override
    public void addInvestInfo(InvestInfo investInfo) {
        investDAO.insertInvestInfo(investInfo);
    }

    @Override
    public List<InvestInfo> queryInvestInfoList() {
        return investDAO.queryInvestInfoList();
    }

    @Override
    public InvestInfo queryInvestInfo(String id) {
        return investDAO.queryInvestInfo(id);
    }
}
