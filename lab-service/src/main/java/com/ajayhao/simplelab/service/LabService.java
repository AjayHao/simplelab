package com.ajayhao.simplelab.service;

/**
 * Created by AjayHao on 2017/6/27.
 */
public interface LabService {

    void pushMsg(String topic, String tag, String msg);

    void receiveMsg(String topic, String tag);
}
