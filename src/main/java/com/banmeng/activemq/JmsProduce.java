package com.banmeng.activemq;

import javax.jms.*;
import java.io.Serializable;

/**
 * @author 半梦
 * @create 2021-03-15 19:59
 */
public interface JmsProduce extends Jms {

    int NON_PERSISTENT = DeliveryMode.NON_PERSISTENT;

    int PERSISTENT = DeliveryMode.PERSISTENT;

    /**
     * 发布信息
     * @param message 信息内容
     */
    void send(Message message) throws JMSException;

    /**
     * 发布信息
     * @param message 字符串类型信息内容
     */
    void send(String message) throws JMSException;

    /**
     * <p>必须首先执行, 该方法会初始化</p>
     * <li>connection</li>
     * <li>session</li>
     * <li>produce</li>
     * @param name 队列或主题名
     */
    void init(String name) throws JMSException;

    /**
     * @return 信息生产者
     */
    MessageProducer getMessageProducer();

    /**
     * <p>该方法会关闭
     * <li>connection</li>
     * <li>session</li>
     * <li>produce</li>
     */
    void close() throws JMSException;

    TextMessage createTextMessage() throws JMSException;

    TextMessage createTextMessage(String text) throws JMSException;

    MapMessage createMapMessage() throws JMSException;

    ObjectMessage createObjectMessage(Serializable obj) throws JMSException;

    ObjectMessage createObjectMessage() throws JMSException;

    BytesMessage createBytesMessage() throws JMSException;

    StreamMessage createStreamMessage() throws JMSException;

    Message createMessage() throws JMSException;

    void setDeliveryMode(int deliveryMode) throws JMSException;
}
