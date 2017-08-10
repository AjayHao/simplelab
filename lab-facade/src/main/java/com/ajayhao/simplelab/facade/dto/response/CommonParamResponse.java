package com.ajayhao.simplelab.facade.dto.response;

import com.ajayhao.simplelab.facade.dto.CommonParamDTO;
import com.ajayhao.simplelab.facade.dto.InvestInfoDTO;
import com.ajayhao.simplelab.facade.dto.common.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */

public class CommonParamResponse extends BaseResponse{

    private static final long serialVersionUID = 2923713057563017849L;

    public CommonParamResponse(){
        super();
    }

    private CommonParamDTO commonParamDTO;

    public CommonParamDTO getCommonParamDTO() {
        return commonParamDTO;
    }

    public void setCommonParamDTO(CommonParamDTO commonParamDTO) {
        this.commonParamDTO = commonParamDTO;
    }
}
