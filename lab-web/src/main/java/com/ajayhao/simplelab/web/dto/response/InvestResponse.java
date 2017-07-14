package com.ajayhao.simplelab.web.dto.response;


import com.ajayhao.simplelab.base.BaseResponse;
import com.ajayhao.simplelab.web.dto.InvestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class InvestResponse implements Serializable {

    private static final long serialVersionUID = -866530435174294000L;

    private String retCode;

    private String retMsg;

    private List<InvestDTO> investDTOs;

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<InvestDTO> getInvestDTOs() {
        return investDTOs;
    }

    public void setInvestDTOs(List<InvestDTO> investDTOs) {
        this.investDTOs = investDTOs;
    }
}
