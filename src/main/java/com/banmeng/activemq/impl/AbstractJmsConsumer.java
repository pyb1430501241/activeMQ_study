package com.banmeng.activemq.impl;

import com.banmeng.activemq.JmsConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 半梦
 * @create 2021-03-20 16:19
 */
public abstract class AbstractJmsConsumer
        extends AbstractJms implements JmsConsumer {

    private volatile ActiveMQConnectionFactory activeMQConnectionFactory;

    private ThreadLocal<MessageConsumer> consumerLocal = new ThreadLocal<>();

    protected ThreadLocal<MessageConsumer> getConsumerLocal() {
        return this.consumerLocal;
    }

    public AbstractJmsConsumer(){
        this.activeMQConnectionFactory = new ActiveMQConnectionFactory();
    }

    @Override
    public Message receive() throws JMSException {
        return this.getMessageConsumer().receive();
    }

    @Override
    public Message receive(long time) throws JMSException {
        return this.getMessageConsumer().receive(time);
    }

    @Override
    public MessageConsumer getMessageConsumer() {
        return this.consumerLocal.get();
    }

    @Override
    public void addMessageListener(MessageListener listener) throws JMSException {
        this.getMessageConsumer().setMessageListener(listener);
    }

    @Override
    public void init(String name) throws JMSException {
        init(name, Boolean.FALSE, AUTO_ACKNOWLEDGE);
    }

    @Override
    public void init(String name, int acknowledgeMode) throws JMSException {
        init(name, Boolean.FALSE, acknowledgeMode);
    }

    @Override
    public void init(String name, boolean transacted) throws JMSException {
        init(name, transacted, AUTO_ACKNOWLEDGE);
    }

    @Override
    public void init(String name, boolean transacted, int acknowledgeMode) throws JMSException {
        this.getConnectionLocal().set(this.createActiveMqConnection());
        this.getConnection().start();
        this.getSessionLocal().set(this.getConnection().createSession(transacted, acknowledgeMode));
    }

    @Override
    public void close() throws JMSException {
        this.getMessageConsumer().close();
        this.getSession().close();
        this.getConnection().close();
    }

    @Override
    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        return this.activeMQConnectionFactory;
    }

}
