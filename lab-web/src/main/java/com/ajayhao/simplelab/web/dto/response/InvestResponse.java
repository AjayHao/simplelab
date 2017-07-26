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

public class InvestResponse extends BaseResponse {

    private static final long serialVersionUID = -866530435174294000L;

    private List<InvestDTO> investDTOs;

    public List<InvestDTO> getInvestDTOs() {
        return investDTOs;
    }

    public void setInvestDTOs(List<InvestDTO> investDTOs) {
        this.investDTOs = investDTOs;
    }
}
