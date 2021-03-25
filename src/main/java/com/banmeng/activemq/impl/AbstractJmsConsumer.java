package com.banmeng.activemq.impl;

import com.banmeng.activemq.JmsConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 半梦
 * @create 2021-03-20 16:19
 */
public abstract class AbstractJmsConsumer implements JmsConsumer {

    private volatile ActiveMQConnectionFactory activeMQConnectionFactory;

    private ThreadLocal<Session> sessionLocal = new ThreadLocal<>();

    private ThreadLocal<MessageConsumer> consumerLocal = new ThreadLocal<>();

    private ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

    protected ThreadLocal<MessageConsumer> getConsumerLocal() {
        return this.consumerLocal;
    }

    protected ThreadLocal<Session> getSessionLocal() {
        return sessionLocal;
    }

    protected ThreadLocal<Connection> getConnectionLocal() {
        return connectionLocal;
    }

    public AbstractJmsConsumer(){
        this.activeMQConnectionFactory = new ActiveMQConnectionFactory();
    }

    @Override
    public Connection createActiveMqConnection() throws JMSException {
        return this.activeMQConnectionFactory.createConnection();
    }

    @Override
    public Connection getConnection() throws JMSException {
        return this.connectionLocal.get();
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
        this.connectionLocal.set(createActiveMqConnection());
        this.getConnection().start();
        this.sessionLocal.set(this.getConnection().createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE));
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

    @Override
    public Session getSession() {
        return this.sessionLocal.get();
    }

}
