package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.service.OraService;
import com.ajayhao.simplelab.dal.OraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AjayHao on 2017/6/22.
 */

@Service
public class OraServiceImpl implements OraService {

    @Autowired
    private OraDAO oraDAO;

    @Override
    public void executeOraFunc() {
        oraDAO.executeFunc();
    }
}
