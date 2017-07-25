package com.ajayhao.simplelab.base;

import com.ajayhao.simplelab.base.dto.BaseDTO;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Ajay Hao on 2016/5/29 0029.
 */
public class BaseRequest extends BaseDTO implements Serializable{
    private static final long serialVersionUID = 5105222290214257880L;

    private String guid;

    private String reqId = UUID.randomUUID().toString().replace("-", "");

    private String apiVersion;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getReqId() {
        return reqId;
    }
}

