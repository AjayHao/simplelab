package com.ajayhao.simplelab.dal;

import com.ajayhao.simplelab.dal.entity.TestInfo;

import java.util.List;

/**
 * Created by AjayHao on 2017/6/27.
 */
public interface LabDAO {
    List<TestInfo> queryTestInfo(String testId);

    int insertTestInfo(TestInfo testInfo);
}
