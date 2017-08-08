package com.ajayhao.simplelab.test.dal;

import com.ajayhao.simplelab.dal.InvestDAO;
import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.InvestInfo;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public class InvestDAOTest extends DALTest{

    @Autowired
    InvestDAO investDAO;

    @Test
    public void testQueryInvestInfoList(){
        List<InvestInfo> list = investDAO.queryInvestInfoList();
        Assert.assertEquals(list.size() , 23);
    }

    @Test
    @Parameters({"id"})
    public void testQueryInvestInfo(String id){
        InvestInfo info = investDAO.queryInvestInfo(id);
        BigDecimal expected = new BigDecimal("50000.00");
        Assert.assertEquals(info.getCost(),expected);
    }

    @Test
    //@Rollback(false)
    @Parameters({"project1","mainchannel1","subchannel1","cost1","income1","begindate1","enddate1"})
    public void testInsertTestInfo(String projectName, String mainchannel, String subchannel, String cost, String income,
                                   String begindate, String enddate){
        InvestInfo i = new InvestInfo();
        i.setProjectName(projectName);
        i.setMainChannel(mainchannel);
        i.setSubChannel(subchannel);
        i.setCost(new BigDecimal(cost));
        i.setIncome(new BigDecimal(income));
        i.setBeginDate(begindate);
        i.setEndDate(enddate);
        int cnt = investDAO.insertInvestInfo(i);
        Assert.assertEquals(cnt , 1);
    }

}
