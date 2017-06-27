package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import com.ajayhao.simplelab.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AjayHao on 2017/6/27.
 */
@Service
public class LabServiceImpl implements LabService{

    @Autowired
    private LabDAO labDAO;

    @Override
    public void pushMsg(String topic, String msg) {
        TestInfo i = new TestInfo();
        i.setTestId(topic);
        i.setTestMsg(msg);
        i.setTestVal(0);
        labDAO.insertTestInfo(i);
    }

    @Override
    public void consumeMsg(String topic, String msg) {
        TestInfo i = new TestInfo();
        i.setTestId(topic);
        i.setTestMsg(msg);
        i.setTestVal(0);
        labDAO.insertTestInfo(i);
    }
}
