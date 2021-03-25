package com.banmeng.activemq;

import javax.jms.JMSException;
import javax.jms.Topic;

/**
 * @author 半梦
 * @create 2021-03-25 20:31
 */
public interface JmsSubscriberConsumer extends JmsConsumer {

    Topic getTopic() throws JMSException;

    boolean getNoLocal() throws JMSException;

    void init(String topicName, String clientId) throws JMSException;

    void init(String topicName, String topicIdent, String clientId) throws JMSException;

}
