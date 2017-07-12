package com.ajayhao.simplelab.test.service;

import com.ajayhao.simplelab.dal.LabDAO;
import com.ajayhao.simplelab.dal.entity.TestInfo;
import com.ajayhao.simplelab.service.listener.impl.MessageListenerImpl;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AjayHao on 2017/7/12.
 *
 * Service层测试,JMockit实现
 */
/*@ContextConfiguration({
        "classpath*:spring/spring-biz-service.xml",
        "classpath*:spring/spring-biz-service.xml"
})*/
public class MessageListenerTest {

    // 表明被修饰实例是将会被自动构建和注入的实例
    @Tested
    MessageListenerImpl messageListener = new MessageListenerImpl();

    // 表明被修饰实例将会自动注入到@Tested修饰的实例中，并且会自动mock掉，除非在测试前被赋值
    @Injectable
    LabDAO labDAO;

    /**
     * 模拟一种场景，假设收到了一条消息
     */
    @Test
    @Parameters({"topic1","body1"})
    public void shouldReturnSuccessWhenConsumeOneMessage(String topic, String body){

        mockInsertTestInfoSuccess();

        //test logic
        List<MessageExt> msgList = new ArrayList<>();
        MessageExt msg = new MessageExt();

        msg.setTopic(topic);
        msg.setBody(body.getBytes());
        msgList.add(msg);

        MessageQueue q = new MessageQueue(topic,"brokerName", 1);
        ConsumeConcurrentlyContext context = new ConsumeConcurrentlyContext(q);
        context.setDelayLevelWhenNextConsume(1);

        ConsumeConcurrentlyStatus status = messageListener.consumeMessage(msgList,context);
        Assert.assertTrue(status == ConsumeConcurrentlyStatus.CONSUME_SUCCESS);
    }

    /**
     * 模拟另一种场景，假设没有收到消息
     */
    @Test
    @Parameters({"topic2","body2"})
    public void shouldReturnReconsumeWhenConsumeOneMessage(String topic, String body){
        //mock
        mockInsertTestInfoFailure();

        //test logic
        List<MessageExt> msgList = new ArrayList<>();
        MessageExt msg = new MessageExt();

        msg.setTopic(topic);
        msg.setBody(body.getBytes());
        msgList.add(msg);

        MessageQueue q = new MessageQueue(topic,"brokerName", 1);
        ConsumeConcurrentlyContext context = new ConsumeConcurrentlyContext(q);
        context.setDelayLevelWhenNextConsume(1);

        ConsumeConcurrentlyStatus status = messageListener.consumeMessage(msgList,context);
        Assert.assertTrue(status == ConsumeConcurrentlyStatus.RECONSUME_LATER);
    }

    private void mockInsertTestInfoSuccess() {
        new Expectations(){
            {
                labDAO.insertTestInfo((TestInfo) any);
                result = 1;
            }
        };
    }

    private void mockInsertTestInfoFailure() {
        new Expectations(){
            {
                labDAO.insertTestInfo((TestInfo) any);
                result = 0;
            }
        };
    }
}
