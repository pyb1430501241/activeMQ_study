package com.banmeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author 半梦
 * @create 2021-03-14 17:03
 */
public interface Jms {

    String QUEUE_NAME = "queue1";

    String TOPIC_NAME = "topic1";

    /**
     * 创建一个新的 connection
     */
    Connection createActiveMqConnection() throws Exception;

    /**
     * 获取当前运作的 connection
     */
    Connection getConnection() throws JMSException;

    /**
     * <p>必须首先执行, 该方法会初始化</p>
     * <li>connection</li>
     * <li>session</li>
     * <li>produce/consumer</li>
     * @param name 队列或主题名
     */
    void init(String name) throws JMSException;

    /**
     * <p>该方法会关闭
     * <li>connection</li>
     * <li>session</li>
     * <li>produce/consumer</li>
     */
    void close() throws JMSException;

    /**
     * @return
     *  返回工厂
     */
    ActiveMQConnectionFactory getActiveMQConnectionFactory();

    /**
     * @return
     *  返回 session
     */
    Session getSession();

}
