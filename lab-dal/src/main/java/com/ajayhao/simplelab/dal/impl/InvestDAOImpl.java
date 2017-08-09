package com.ajayhao.simplelab.dal.impl;

import com.ajayhao.simplelab.dal.InvestDAO;
import com.ajayhao.simplelab.dal.entity.InvestInfoDO;
import com.ajayhao.simplelab.dal.mapper.InvestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
@Repository("investDAO")
public class InvestDAOImpl implements InvestDAO{

    @Autowired
    private InvestMapper investMapper;

    @Override
    public List<InvestInfoDO> queryInvestInfoList() {
        return investMapper.queryInvestInfoList();
    }

    @Override
    public InvestInfoDO queryInvestInfo(String id) {
        return investMapper.queryInvestInfo(id);
    }

    @Override
    public int insertInvestInfo(InvestInfoDO investInfo) {
        return investMapper.insertInvestInfo(investInfo);
    }

    @Override
    public int deleteInvestInfo(InvestInfoDO investInfo) {
        return investMapper.deleteInvestInfo(investInfo);
    }

}
