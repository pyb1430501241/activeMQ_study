package com.banmeng.activemq;

import javax.jms.JMSException;
import javax.jms.Topic;

/**
 * @author 半梦
 * @create 2021-03-25 20:31
 */
public interface JmsSubscriberConsumer extends JmsConsumer {

    /**
     * 获取管理的主题
     * @return <p>主题
     */
    Topic getTopic() throws JMSException;

    boolean getNoLocal() throws JMSException;

    /**
     * init 的重载
     * @param topicName 主题名
     * @param clientId 客户名称
     */
    void init(String topicName, String clientId) throws JMSException;

    /**
     * init 的重载
     * @param topicName 主题名
     * @param topicIdent 订阅描述
     * @param clientId 客户名称, 默认为 UUID
     */
    void init(String topicName, String topicIdent, String clientId) throws JMSException;

    /**
     * init 的重载
     * @see com.banmeng.activemq.Jms#init(String, boolean, int)
     * @param topicName 主题名
     * @param topicIdent 订阅描述
     * @param clientId 客户名称
     * @param transacted 开启事务
     */
    void init(String topicName, String topicIdent, String clientId, boolean transacted) throws JMSException;

    /**
     * init 的重载
     * @see com.banmeng.activemq.Jms#init(String, boolean, int)
     * @param topicName 主题名
     * @param topicIdent 订阅描述
     * @param clientId 客户名称
     * @param acknowledgeMode 签收方式
     */
    void init(String topicName, String topicIdent, String clientId, int acknowledgeMode) throws JMSException;

    /**
     * init 的重载
     * @see com.banmeng.activemq.JmsSubscriberConsumer#init(String, String, String, int)
     * @see com.banmeng.activemq.JmsSubscriberConsumer#init(String, String, String, boolean)
     * @param topicName 主题名
     * @param topicIdent 订阅描述
     * @param clientId 客户名称
     * @param transacted 开启事务
     * @param acknowledgeMode 签收方式
     */
    void init(String topicName, String topicIdent, String clientId, boolean transacted, int acknowledgeMode) throws JMSException;

}
