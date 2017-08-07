package com.ajayhao.simplelab.test.dal;

import com.ajayhao.simplelab.base.exception.BaseException;
import com.ajayhao.simplelab.dal.CommonParamDAO;
import com.ajayhao.simplelab.dal.entity.CommonParamDO;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by AjayHao on 2017/7/26.
 */
public class CommonParamDAOTest extends DALTest{
    @Autowired
    CommonParamDAO commonParamDAO;

    @Mock
    CommonParamDAO commonParamDAOMock;

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


    @Test
    public void testQueryCommonParamWithMock(){
        mockSetUp();
        CommonParamDO commonParamDO = null;
        try {
            commonParamDO = commonParamDAOMock.queryByGroupAndName("aaa","bbb");
        } catch (BaseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(commonParamDO.getParamValue(), "val");
    }

    private void mockSetUp() {
        CommonParamDO mockCommonParamDO = new CommonParamDO();
        mockCommonParamDO.setParamGroup("abc");
        mockCommonParamDO.setParamName("name");
        mockCommonParamDO.setParamValue("val");
        try {
            when(commonParamDAOMock.queryByGroupAndName(anyString(),anyString())).thenReturn(mockCommonParamDO);
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }
}
