package com.banmeng.activemq.test;

import com.banmeng.activemq.support.queue.JmsQueueProduce;
import com.banmeng.activemq.support.topic.JmsTopicProduce;
import com.banmeng.activemq.Jms;
import com.banmeng.activemq.JmsProduce;
import org.junit.Test;

import javax.jms.MapMessage;

/**
 * @author 半梦
 * @create 2021-03-20 17:28
 */
public class ProduceTest {


    /**
     * queue
     */
    @Test
    public void testSendByQueue() throws Exception {
        JmsProduce produce = JmsQueueProduce.getInstance();
        produce.init(Jms.QUEUE_NAME);
        for(int i = 0; i < 6; i++) {
            produce.send("你好 世界！");
        }
        produce.close();
    }

    /**
     * topic
     */
    @Test
    public void testSendByTopic() throws Exception {
        JmsProduce produce = JmsTopicProduce.getInstance();
        produce.init(Jms.TOPIC_NAME);
        for (int i = 0; i < 6; i++) {
            produce.send("Hello World!");
        }
        produce.close();
    }

    /**
     * Queue
     */
    @Test
    public void testSendMapByQueue() throws Exception {
        JmsProduce produce = JmsQueueProduce.getInstance();
        produce.init(Jms.QUEUE_NAME);
        produce.setDeliveryMode(JmsProduce.NON_PERSISTENT);
        MapMessage mapMessage = produce.createMapMessage();
        mapMessage.setString("k1", "message---->k1");
        mapMessage.setStringProperty("c1", "VIP!");
        produce.send(mapMessage);
        produce.close();
    }

}
