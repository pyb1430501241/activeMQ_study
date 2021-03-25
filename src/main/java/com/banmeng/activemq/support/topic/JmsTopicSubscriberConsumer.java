package com.banmeng.activemq.support.topic;

import com.banmeng.activemq.JmsSubscriberConsumer;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * @author 半梦
 * @create 2021-03-25 19:50
 */
public final class JmsTopicSubscriberConsumer
        extends JmsTopicConsumer implements JmsSubscriberConsumer {

    private JmsTopicSubscriberConsumer() {
        super();
    }

    private static final JmsSubscriberConsumer CONSUMER = new JmsTopicSubscriberConsumer();

    public static JmsSubscriberConsumer getInstance() {
        return CONSUMER;
    }

    @Override
    public void init(String topicName) throws JMSException {
        init(topicName, "张三");
    }

    @Override
    public void init(String topicName, String clientId) throws JMSException {
       init(topicName, "Status", clientId);
    }

    @Override
    public void init(String topicName, String topicIdent, String clientId) throws JMSException {
        this.getConnectionLocal().set(createActiveMqConnection());
        this.getConnection().setClientID(clientId);
        this.getSessionLocal().set(this.getConnection().createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE));
        this.getConsumerLocal().set(this.getSession().
                createDurableSubscriber(this.getSession().createTopic(topicName), topicIdent));
        this.getConnection().start();
    }

    @Override
    public Topic getTopic() throws JMSException {
        return ((TopicSubscriber)this.getMessageConsumer()).getTopic();
    }

    @Override
    public boolean getNoLocal() throws JMSException {
        return ((TopicSubscriber)this.getMessageConsumer()).getNoLocal();
    }

}
