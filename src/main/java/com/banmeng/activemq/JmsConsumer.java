package com.banmeng.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

/**
 * @author 半梦
 * @create 2021-03-15 19:58
 */
public interface JmsConsumer extends Jms {

    /**
     * 接受一个信息, 永远阻塞等待
     * @return 信息
     * @see Message
     */
    Message receive() throws JMSException;

    /**
     * 接收一个信息, 等待直至时间结束
     * @param time 等待时间
     * @return 信息
     * @see Message
     */
    Message receive(long time) throws JMSException;

    /**
     * @return 信息消费者
     */
    MessageConsumer getMessageConsumer();

    /**
     * 添加一个监听器, 用于监听是否有信息, 如有则执行
     * @see MessageListener#onMessage(Message) 方法
     * @param listener 消息监听器
     */
    void addMessageListener(MessageListener listener) throws JMSException;

    /**
     * <p>必须首先执行, 该方法会初始化</p>
     * <li>connection</li>
     * <li>session</li>
     * <li>consumer</li>
     * @param name 队列或主题名
     */
    void init(String name) throws JMSException;

    /**
     * <p>该方法会关闭
     * <li>connection</li>
     * <li>session</li>
     * <li>consumer</li>
     */
    void close() throws JMSException;

}
