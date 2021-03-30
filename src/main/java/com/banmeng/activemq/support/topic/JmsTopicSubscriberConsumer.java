package com.banmeng.activemq.support.topic;

import com.banmeng.activemq.JmsSubscriberConsumer;

import javax.jms.JMSException;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import java.util.UUID;

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
        init(topicName, UUID.randomUUID().toString().replaceAll("[-]", ""));
    }

    @Override
    public void init(String topicName, String clientId) throws JMSException {
       init(topicName, "Status", clientId);
    }

    @Override
    public void init(String topicName, String topicIdent, String clientId) throws JMSException {
        init(topicName, topicIdent, clientId, Boolean.FALSE, AUTO_ACKNOWLEDGE);
    }

    @Override
    public Topic getTopic() throws JMSException {
        return ((TopicSubscriber)this.getMessageConsumer()).getTopic();
    }

    @Override
    public boolean getNoLocal() throws JMSException {
        return ((TopicSubscriber)this.getMessageConsumer()).getNoLocal();
    }

    @Override
    public void init(String topicName, boolean transacted, int acknowledgeMode) throws JMSException {
        init(topicName, "status", UUID.randomUUID().toString().
                replaceAll("[-]", ""), transacted, acknowledgeMode);
    }

    @Override
    public void init(String topicName, String topicIdent, String clientId, boolean transacted) throws JMSException {
        init(topicName, topicIdent, clientId, transacted, AUTO_ACKNOWLEDGE);
    }

    @Override
    public void init(String topicName, String topicIdent, String clientId, int acknowledgeMode) throws JMSException {
        init(topicName, topicIdent, clientId, Boolean.FALSE, acknowledgeMode);
    }

    @Override
    public void init(String topicName, String topicIdent, String clientId, boolean transacted, int acknowledgeMode) throws JMSException {
        this.getConnectionLocal().set(createActiveMqConnection());
        this.getConnection().setClientID(clientId);
        this.getSessionLocal().set(this.getConnection().createSession(transacted, acknowledgeMode));
        this.getConsumerLocal().set(this.getSession().
                createDurableSubscriber(this.getSession().createTopic(topicName), topicIdent));
        this.getConnection().start();
    }

}
