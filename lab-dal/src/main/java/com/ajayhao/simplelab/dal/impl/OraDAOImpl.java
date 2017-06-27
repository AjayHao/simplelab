package com.ajayhao.simplelab.dal.impl;

import com.ajayhao.simplelab.dal.OraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * Created by AjayHao on 2017/6/22.
 */
@Repository
public class OraDAOImpl implements OraDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /******
      Function Demo:
         create or replace function insertsms(i_eno number,o_title out varchar2,salch in number) return integer
         as
         begin
         o_title := '返回中文';
         return 10;
         end;
    ******/
    @Override
    public void executeFunc() {
        String selSql = "{? = call insertsms(?,?,?)}"; // function
        int cntNum = 0;
        final String[] str = new String[1];
        try{

            cntNum = jdbcTemplate.execute(selSql, new CallableStatementCallback<Integer>() {
                public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                    cs.registerOutParameter(1, java.sql.Types.VARCHAR);
                    cs.setInt(2, 1);
                    cs.registerOutParameter(3, java.sql.Types.VARCHAR);
                    cs.setInt(4, 2);
                    cs.execute();
                    str[0] = cs.getString(3);
                    return cs.getInt(1);
                }
            });
        }catch(DataAccessException e){
            //
        }

        Assert.isTrue(cntNum == 10);
        Assert.isTrue(str[0].equals("返回中文"));
    }

}
