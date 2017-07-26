package com.ajayhao.simplelab.test.dal;

import com.ajayhao.simplelab.base.BaseException;
import com.ajayhao.simplelab.dal.CommonParamDAO;
import com.ajayhao.simplelab.dal.entity.CommonParamDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/26.
 */
public class CommonParamDAOTest extends DALTest{
    @Autowired
    CommonParamDAO commonParamDAO;

    @Test
    public void testQueryCommonParam(){
        List<CommonParamDO> l = commonParamDAO.queryAll();
        Assert.assertEquals(l.size(), 1);
    }

    @Test
    public void testQueryCommonParamByGroup(){
        List<CommonParamDO> l = commonParamDAO.queryByGroup("aaa");
        Assert.assertEquals(l.size(), 1);
    }

    @Test
    public void testQueryCommonParamByGroupAndName(){
        CommonParamDO commonParamDO = null;
        try {
            commonParamDO = commonParamDAO.queryByGroupAndName("aaa","bbb");
        } catch (BaseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(commonParamDO.getParamValue(), "ccc");
    }
}
