package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.service.LabService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AjayHao on 2017/6/27.
 */
@Service
public class LabServiceImpl implements LabService{

    @Autowired
    private DefaultMQProducer mqProducer;

    @Override
    public void pushMsg(String tag, String msg) {
        try {
            Message message = new Message("PushTopic", tag, msg.getBytes());
            SendResult result = mqProducer.send(message);
            System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
