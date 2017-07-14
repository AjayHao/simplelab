package com.ajayhao.simplelab.test.dal;

import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/11.
 */

public class LabDAOTest extends DALTest {

    @Autowired
    LabDAO labDAO;

    @Test
    public void testQueryTestInfo(){
        List<TestInfo> list = labDAO.queryTestInfo("ajay");
        Assert.assertEquals(list.size() , 0);
    }

    @Test
    //@Rollback(false)
    @Parameters({"testId","testMsg","testVal"})
    public void testInsertTestInfo(@Optional("111") String testId, String testMsg, int testVal){
        TestInfo t = new TestInfo();
        t.setTestId(testId);
        t.setTestMsg(testMsg);
        t.setTestVal(testVal);
        int i = labDAO.insertTestInfo(t);
        Assert.assertEquals(i , 1);
    }

}
