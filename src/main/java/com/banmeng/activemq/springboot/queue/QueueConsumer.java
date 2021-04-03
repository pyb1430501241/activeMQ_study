package com.banmeng.activemq.springboot.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * SpringBoot 整合 activeMq 队列消费者
 * @author 半梦
 * @create 2021-04-03 16:46
 */
@Component
public class QueueConsumer {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    public void receive() {
        String s = template.receiveAndConvert(queue, String.class);
        System.out.println(s);
    }

    @JmsListener(destination = "${queue}")
    public void receiveListener(TextMessage message) throws Exception {
        System.out.println("queue: " + message.getText());
    }

}
