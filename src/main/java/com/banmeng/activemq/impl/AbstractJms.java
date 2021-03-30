package com.banmeng.activemq.impl;

import com.banmeng.activemq.Jms;

import javax.jms.*;

/**
 * @author 半梦
 * @create 2021-03-29 18:36
 */
public abstract class AbstractJms implements Jms {

    private ThreadLocal<Session> sessionLocal = new ThreadLocal<>();

    private ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

    protected ThreadLocal<Session> getSessionLocal() {
        return sessionLocal;
    }

    protected ThreadLocal<Connection> getConnectionLocal() {
        return connectionLocal;
    }

    @Override
    public Connection createActiveMqConnection() throws JMSException {
        return getActiveMQConnectionFactory().createConnection();
    }

    @Override
    public Connection getConnection() throws JMSException {
        return this.connectionLocal.get();
    }

    @Override
    public Session getSession() {
        return this.sessionLocal.get();
    }

    @Override
    public void commit() throws JMSException {
        this.getSession().commit();
    }

    @Override
    public void rollback() throws JMSException {
        this.getSession().rollback();
    }

}
