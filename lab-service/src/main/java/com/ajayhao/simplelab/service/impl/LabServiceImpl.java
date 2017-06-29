package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.service.LabService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by AjayHao on 2017/6/27.
 */

public class LabServiceImpl implements LabService{

    DefaultMQProducer mqProducer;

    public void setMqProducer(DefaultMQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

    @Override
    public void pushMsg(String tag, String msg) {
        try {
            Message message = new Message("PushTopic", "push", "1",  msg.getBytes());
            SendResult result = mqProducer.send(message);
            System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
