package com.ajayhao.simplelab.dal.mapper;

import com.ajayhao.simplelab.dal.entity.TestInfo;

import java.util.List;

/**
 * Created by haozj on 2017/6/29.
 */
public interface LabMapper {

    List<TestInfo> queryTestInfo(String testId);

    int insertTestInfo(TestInfo testInfo);
}
