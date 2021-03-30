package com.banmeng.activemq.test;

import com.banmeng.activemq.JmsConsumer;
import com.banmeng.activemq.JmsSubscriberConsumer;
import com.banmeng.activemq.support.queue.JmsQueueConsumer;
import com.banmeng.activemq.support.topic.JmsTopicConsumer;
import com.banmeng.activemq.Jms;
import com.banmeng.activemq.support.topic.JmsTopicSubscriberConsumer;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * @author 半梦
 * @create 2021-03-20 17:28
 */
@SuppressWarnings("all")
public class ConsumerTest {

    /**
     * 单个消费者阻塞写法
     */
    @Test
    public void testReceive() throws Exception{
        JmsConsumer consumer = JmsQueueConsumer.getInstance();
        consumer.init(Jms.QUEUE_NAME);
        while (true) {
            TextMessage receive = (TextMessage) consumer.receive(3000L);
            if (receive != null) {
                System.out.println(receive.getText());
            } else {
                break;
            }
        }
        consumer.close();
    }

    @Test
    public void testReceiveMapMessage() throws Exception{
        JmsConsumer consumer = JmsQueueConsumer.getInstance();
        consumer.init(Jms.QUEUE_NAME);
        while (true) {
            MapMessage receive = (MapMessage) consumer.receive(3000L);
            if (receive != null) {
                System.out.println(receive.getString("k1"));
            } else {
                break;
            }
        }
        consumer.close();
    }

    /**
     * 1. 先生产, 启动一号消费者, 再启动二号消费者, 那二号消费者还能消费吗？
     *  NO!
     * 2. 先启动两个消费者, 再生产6条消息, 消费情况为？
     * 一人一半！
     *  监听器, 且验证多线程情况（多个消费者）同时拿取消息的逻辑
     */
    @Test
    public void testListener() throws Exception {
        new Thread(() -> {
            JmsConsumer consumer = JmsQueueConsumer.getInstance();
            try {
                consumer.init(Jms.QUEUE_NAME);
            } catch (JMSException ignored) {
            }
            try {
                consumer.addMessageListener(message -> {
                    try {
                        if(message instanceof TextMessage) {
                            String text = ((TextMessage) message).getText();
                            System.out.println("一号消费者: " + text);
                        }
                    } catch (JMSException e) {
                        System.out.println(e.getMessage());
                    }
                });
            } catch (JMSException ignored) {
            }
            try {
                System.in.read();
                consumer.close();
            } catch (JMSException | IOException ignored) {
            }
        }).start();

        new Thread(() -> {
            JmsConsumer consumer = JmsQueueConsumer.getInstance();
            try {
                consumer.init(Jms.QUEUE_NAME);
            } catch (JMSException ignored) {
            }
            try {
                consumer.addMessageListener(message -> {
                    try {
                        if(message instanceof TextMessage) {
                            String text = ((TextMessage) message).getText();
                            System.out.println("二号消费者: " + text);
                        }
                    } catch (JMSException e) {
                        System.out.println(e.getMessage());
                    }
                });
            } catch (JMSException ignored) {
            }
            try {
                System.in.read();
                consumer.close();
            } catch (JMSException | IOException ignored) {
            }
        }).start();
    }

    @Test
    public void testPropertyByListener() throws Exception {
        JmsConsumer consumer = JmsQueueConsumer.getInstance();
        consumer.init(Jms.QUEUE_NAME);
        consumer.addMessageListener(message -> {
            if(message instanceof MapMessage) {
                try {
                    String text = ((MapMessage) message).getString("k1");
                    String property = ((MapMessage) message).getStringProperty("c1");
                    System.out.println("消息: " + text);
                    System.out.println("标签: " + property);
                } catch (JMSException e) {
                }
            }
        });
        System.in.read();
        consumer.close();
    }


    /*-----------------Topic-----------------------------------*/

    /**
     * 单个消费者
     */
    @Test
    public void testTopic() throws Exception {
        JmsConsumer consumer = JmsTopicConsumer.getInstance();
        consumer.init(Jms.TOPIC_NAME);
        consumer.addMessageListener(message -> {
            if(message instanceof TextMessage) {
                try {
                    String text = ((TextMessage) message).getText();
                    System.out.println("消费者: " + text);
                } catch (JMSException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        System.in.read();
        consumer.close();
    }

    /**
     * 多个消费者, 人人都有！(必须进队后再发布消息才可以拿到
     */
    @Test
    public void testTopicByThread() throws Exception {
        new Thread(() -> {
            JmsConsumer consumer = JmsTopicConsumer.getInstance();
            try {
                consumer.init(Jms.TOPIC_NAME);
            } catch (JMSException e) {
            }
            try {
                consumer.addMessageListener(message -> {
                    try {
                        if(message instanceof TextMessage) {
                            String text = ((TextMessage) message).getText();
                            System.out.println("一号消费者: " + text);
                        }
                    } catch (JMSException e) {
                        System.out.println(e.getMessage());
                    }
                });
            } catch (JMSException e) {
            }
            try {
                System.in.read();
                consumer.close();
            } catch (JMSException | IOException e) {
            }
        }).start();

        new Thread(() -> {
            JmsConsumer consumer = JmsTopicConsumer.getInstance();
            try {
                consumer.init(Jms.TOPIC_NAME);
            } catch (JMSException e) {
            }
            try {
                consumer.addMessageListener(message -> {
                    try {
                        if(message instanceof TextMessage) {
                            String text = ((TextMessage) message).getText();
                            System.out.println("二号消费者: " + text);
                        }
                    } catch (JMSException e) {
                        System.out.println(e.getMessage());
                    }
                });
            } catch (JMSException e) {
            }
            try {
                System.in.read();
                consumer.close();
            } catch (JMSException | IOException e) {
            }
        }).start();
        System.in.read();
    }

    /**
     * 单个消费者
     */
    @Test
    public void testTopicSubscriber() throws Exception {
        JmsConsumer consumer = JmsTopicSubscriberConsumer.getInstance();
        consumer.init(Jms.TOPIC_NAME, true, Jms.CLIENT_ACKNOWLEDGE);
        consumer.addMessageListener(message -> {
            if(message instanceof TextMessage) {
                try {
                    String text = ((TextMessage) message).getText();
                    System.out.println("收到持久化订阅, 消费者: " + text);
                    message.acknowledge();
                } catch (JMSException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        consumer.commit();
        System.in.read();
        consumer.close();
    }

}
