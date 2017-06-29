package com.ajayhao.simplelab.dal.impl;

import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import com.ajayhao.simplelab.dal.mapper.LabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haozj on 2017/6/29.
 */
@Repository("labDAO")
public class LabDAOImpl implements LabDAO{

    @Autowired
    LabMapper labMapper;

    @Override
    public List<TestInfo> queryTestInfo(String testId) {
        return labMapper.queryTestInfo(testId);
    }

    @Override
    public int insertTestInfo(TestInfo testInfo) {
        return labMapper.insertTestInfo(testInfo);
    }
}
