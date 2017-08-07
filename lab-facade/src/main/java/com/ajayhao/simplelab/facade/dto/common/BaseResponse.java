package com.ajayhao.simplelab.facade.dto.common;

import com.ajayhao.simplelab.facade.enums.BizCode;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2016/5/29 0029.
 */
public class BaseResponse extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 6784377605497392229L;

    private String respCode;

    private String respMsg;

    private String respId = UUID.randomUUID().toString().replace("-", "");

    private String reqId;

    public BaseResponse() {
        this.respCode = BizCode.SUCCESS.getCode();
        this.respMsg = BizCode.SUCCESS.getMessage();
    }

    public BaseResponse(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getRespId() {
        return respId;
    }

}