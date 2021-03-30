package com.banmeng.activemq.support.topic;

import com.banmeng.activemq.JmsConsumer;
import com.banmeng.activemq.impl.AbstractJmsConsumer;

import javax.jms.JMSException;

/**
 * @author 半梦
 * @create 2021-03-20 16:18
 */
public class JmsTopicConsumer extends AbstractJmsConsumer {

    JmsTopicConsumer(){
        super();
    }

    private static final JmsConsumer CONSUMER = new JmsTopicConsumer();

    public static JmsConsumer getInstance() {
        return CONSUMER;
    }

    @Override
    public void init(String topicName, boolean transacted, int acknowledgeMode) throws JMSException {
        super.init(topicName, transacted, acknowledgeMode);
        this.getConsumerLocal().set(this.getSession().createConsumer(this.getSession().createTopic(topicName)));
    }

}
