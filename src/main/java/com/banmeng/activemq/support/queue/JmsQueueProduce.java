package com.banmeng.activemq.support.queue;

import com.banmeng.activemq.JmsProduce;
import com.banmeng.activemq.impl.AbstractJmsProduce;

import javax.jms.*;

/**
 * @author 半梦
 * @create 2021-03-14 17:05
 */
public class JmsQueueProduce extends AbstractJmsProduce {

    private JmsQueueProduce(){
        super();
    }

    private static final JmsProduce PRODUCE = new JmsQueueProduce();

    public static JmsProduce getInstance() {
        return PRODUCE;
    }

    @Override
    public void init(String queueName, boolean transacted, int acknowledgeMode) throws JMSException {
        super.init(queueName, transacted, acknowledgeMode);
        this.getProducerLocal().set(this.getSession().createProducer(this.getSession().createQueue(queueName)));
        this.setDeliveryMode(PERSISTENT);
    }



}
