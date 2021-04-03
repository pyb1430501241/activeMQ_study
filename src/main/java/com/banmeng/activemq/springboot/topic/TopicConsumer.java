package com.banmeng.activemq.springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * SpringBoot 整合 activeMq 订阅消费者
 * @author 半梦
 * @create 2021-04-03 17:03
 */
@Component
public class TopicConsumer {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Topic topic;

    public void receive() {
        String s = template.receiveAndConvert(topic, String.class);
        System.out.println(s);
    }


    @JmsListener(destination = "${topic}")
    public void receiveListener(TextMessage message) throws Exception{
        System.out.println("topic: " + message.getText());
    }

}
