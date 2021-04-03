package com.banmeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 尝试自己封装
 * @author 半梦
 * @create 2021-03-14 17:03
 */
public interface Jms {

    String QUEUE_NAME = "queue1";

    String TOPIC_NAME = "topic1";

    int AUTO_ACKNOWLEDGE = Session.AUTO_ACKNOWLEDGE;

    int SESSION_TRANSACTED = Session.SESSION_TRANSACTED;

    int CLIENT_ACKNOWLEDGE = Session.CLIENT_ACKNOWLEDGE;

    @SuppressWarnings("all")
    int DUPS_OK_ACKNOWLEDGE = Session.DUPS_OK_ACKNOWLEDGE;

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
     * init 的重载
     * @param name 队列或主题名
     * @param acknowledgeMode 签收方式
     * @see com.banmeng.activemq.Jms#init(String)
     * @see com.banmeng.activemq.Jms#AUTO_ACKNOWLEDGE
     *   <li>自动签收
     * @see com.banmeng.activemq.Jms#SESSION_TRANSACTED
     *   <li>事务级签收
     * @see com.banmeng.activemq.Jms#CLIENT_ACKNOWLEDGE
     *   <li>手动签收
     * @see com.banmeng.activemq.Jms#DUPS_OK_ACKNOWLEDGE
     *   <li>允许重复签收
     * @see Message#acknowledge()
     *  <li>如手动签收, 则必须调用此签收方法, 但如果使用了事务, 且已提交事务, 则会自动签收
     */
    void init(String name, int acknowledgeMode) throws JMSException;

    /**
     * init 的重载
     * @see com.banmeng.activemq.Jms#init(String)
     * @param name 队列或主题名
     * @param transacted 是否开启事务, 事务优先级大于签收。
     * <p>默认为不开启事务</p>
     * <li><code>transacted</code> = true 开启事务, 需手动提交事务并回滚</li>
     * <li><code>transacted</code> = false 不开启事务</li>
     */
    void init(String name, boolean transacted) throws JMSException;

    /**
     * init 的重载
     * @see com.banmeng.activemq.Jms#init(String, int)
     * @see com.banmeng.activemq.Jms#init(String, boolean)
     * @param name 队列或主题名
     */
    void init(String name, boolean transacted, int acknowledgeMode) throws JMSException;

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

    /**
     * 提交事务, 必须为事务 session
     * @see com.banmeng.activemq.Jms#init(String, boolean)
     * @see Session
     */
    void commit() throws JMSException;

    /**
     * 回滚, 必须为事务 session
     * @see com.banmeng.activemq.Jms#init(String, boolean)
     * @see Session
     */
    void rollback() throws JMSException;

}
