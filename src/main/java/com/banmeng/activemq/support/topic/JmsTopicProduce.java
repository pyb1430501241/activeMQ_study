package com.banmeng.activemq.support.topic;

import com.banmeng.activemq.impl.AbstractJmsProduce;
import com.banmeng.activemq.JmsProduce;

import javax.jms.JMSException;

/**
 * @author 半梦
 * @create 2021-03-20 16:52
 */
public class JmsTopicProduce extends AbstractJmsProduce {

    private JmsTopicProduce(){
        super();
    }

    private static final JmsProduce PRODUCE = new JmsTopicProduce();

    public static JmsProduce getInstance() {
        return PRODUCE;
    }

    @Override
    public void init(String topicName) throws JMSException {
        super.init(topicName);
        this.getProducerLocal().set(this.getSession().createProducer(this.getSession().createTopic(topicName)));
        this.setDeliveryMode(PERSISTENT);
    }

}
