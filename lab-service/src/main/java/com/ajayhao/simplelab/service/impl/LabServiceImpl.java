package com.ajayhao.simplelab.service.impl;

import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import com.ajayhao.simplelab.service.LabService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AjayHao on 2017/6/27.
 */
@Service
public class LabServiceImpl implements LabService{

    @Autowired
    private LabDAO labDAO;

    @Override
    public void pushMsg(String topic, String tag, String msg) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.137.1:9876");
        try {
            producer.start();

            Message message = new Message(topic,
                    "push",
                    "1",
                    msg.getBytes());

            SendResult result = producer.send(message);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            message = new Message(topic,
                    "push",
                    "2",
                    msg.getBytes());

            result = producer.send(message);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            message = new Message(topic,
                    "pull",
                    "1",
                    msg.getBytes());

            result = producer.send(message);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

    @Override
    public void receiveMsg(final String topic, String tag) {

        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("PushConsumer");

        consumer.setNamesrvAddr("192.168.137.1:9876");
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe(topic, tag);
            //程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(
                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(
                    new MessageListenerConcurrently() {
                        public ConsumeConcurrentlyStatus consumeMessage(
                                List<MessageExt> list,
                                ConsumeConcurrentlyContext Context) {
                            Message msg = list.get(0);
                            System.out.println("consume message:"+msg.toString());
                            doConsume(topic, msg.toString());
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    }
            );
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doConsume(String topic, String message){
        TestInfo i = new TestInfo();
        i.setTestId(topic);
        i.setTestMsg(message);
        i.setTestVal(0);
        labDAO.insertTestInfo(i);
    }

}
