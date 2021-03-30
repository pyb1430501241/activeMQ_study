package com.banmeng.activemq.support.queue;

import com.banmeng.activemq.JmsConsumer;
import com.banmeng.activemq.impl.AbstractJmsConsumer;

import javax.jms.*;

/**
 * @author 半梦
 * @create 2021-03-14 17:38
 */
public class JmsQueueConsumer extends AbstractJmsConsumer {

    private JmsQueueConsumer() {
        super();
    }

    private static final JmsConsumer CONSUMER = new JmsQueueConsumer();

    public static JmsConsumer getInstance() {
        return CONSUMER;
    }

    @Override
    public void init(String queueName, boolean transacted, int acknowledgeMode) throws JMSException {
        super.init(queueName, transacted, acknowledgeMode);
        this.getConsumerLocal().set(this.getSession().createConsumer(this.getSession().createQueue(queueName)));
    }

}
