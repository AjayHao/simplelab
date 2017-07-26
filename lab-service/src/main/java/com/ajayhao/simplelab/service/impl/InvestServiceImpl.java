package com.ajayhao.simplelab.service.impl;

import com.ajayhao.core.util.CoreDateUtils;
import com.ajayhao.simplelab.dal.InvestDAO;
import com.ajayhao.simplelab.dal.entity.InvestInfo;
import com.ajayhao.simplelab.service.InvestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
@Service("investService")
public class InvestServiceImpl implements InvestService {

    Logger log = LoggerFactory.getLogger(InvestServiceImpl.class);

    private static final BigDecimal DAYS_OF_YEAR = new BigDecimal("365");
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100.00");

    @Autowired
    InvestDAO investDAO;

    @Override
    public void addInvestInfo(InvestInfo investInfo) {
        investDAO.insertInvestInfo(investInfo);
    }

    @Override
    public List<InvestInfo> queryInvestInfoList() {
        log.info("","");
        return investDAO.queryInvestInfoList();
    }

    @Override
    public InvestInfo queryInvestInfo(String id) {
        return investDAO.queryInvestInfo(id);
    }

    @Override
    /* 计算年化收益率 */
    public BigDecimal calculateAnnualYield(InvestInfo investInfo) {
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
                new BigDecimal(CoreDateUtils.days(CoreDateUtils.parseToDate(beginDate, "yyyyMMdd"),
                                    CoreDateUtils.parseToDate(endDate, "yyyyMMdd"))+1);

        BigDecimal b = income.divide(cost, 8, RoundingMode.HALF_DOWN);
        b = b.multiply(DAYS_OF_YEAR).multiply(ONE_HUNDRED).divide(daysInterval, 2 , BigDecimal.ROUND_HALF_DOWN);
        return b;
    }
}
