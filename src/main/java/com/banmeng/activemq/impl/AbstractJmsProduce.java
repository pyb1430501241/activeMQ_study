package com.banmeng.activemq.impl;

import com.banmeng.activemq.JmsProduce;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

/**
 * @author 半梦
 * @create 2021-03-20 16:41
 */
public abstract class AbstractJmsProduce
        extends AbstractJms implements JmsProduce {

    private ThreadLocal<MessageProducer> producerLocal = new ThreadLocal<>();

    protected ThreadLocal<MessageProducer> getProducerLocal() {
        return this.producerLocal;
    }

    public AbstractJmsProduce() {
        super();
    }

    @Override
    public void send(Message message) throws JMSException {
        this.getMessageProducer().send(message);
    }

    @Override
    public void send(String message) throws JMSException {
        this.getMessageProducer().send(createTextMessage(message));
    }

    @Override
    public MessageProducer getMessageProducer() {
        return this.producerLocal.get();
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
        this.getConnectionLocal().set(createActiveMqConnection());
        this.getConnection().start();
        this.getSessionLocal().set(this.getConnection().createSession(transacted, acknowledgeMode));
    }

    @Override
    public void close() throws JMSException {
        this.getMessageProducer().close();
        this.getSession().close();
        this.getConnection().close();
    }

    public TextMessage createTextMessage(String text) throws JMSException {
        return this.getSession().createTextMessage(text);
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        return this.getSession().createTextMessage();
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        return this.getSession().createMapMessage();
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable obj) throws JMSException {
        return this.getSession().createObjectMessage(obj);
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        return this.getSession().createObjectMessage();
    }

    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        return this.getSession().createBytesMessage();
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        return this.getSession().createStreamMessage();
    }

    @Override
    public Message createMessage() throws JMSException {
        return this.getSession().createMessage();
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {
        this.getMessageProducer().setDeliveryMode(deliveryMode);
    }

}
