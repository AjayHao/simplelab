package com.ajayhao.simplelab.service.listener.impl;

import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by AjayHao on 2017/6/29.
 */
public class MessageListenerImpl implements MessageListenerConcurrently {

    @Resource(name="labDAO")
    private LabDAO labDAO;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext context) {
        for (MessageExt messageExt : msgList) {
            System.out.println("consume message:"+ messageExt.toString());
            doConsume("PushTopic", messageExt.toString());
        }
        System.out.println("getDelayLevelWhenNextConsume="+context.getDelayLevelWhenNextConsume()+"getMessageQueue="+context.getMessageQueue().toString()+"getDelayLevelWhenNextConsume="+context.getDelayLevelWhenNextConsume());
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }


    private void doConsume(String topic, String message){
        TestInfo i = new TestInfo();
        i.setTestId(topic);
        i.setTestMsg(message);
        i.setTestVal(0);
        labDAO.insertTestInfo(i);
    }

}
