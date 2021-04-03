package com.banmeng.activemq.springboot.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @author 半梦
 * @create 2021-04-03 16:51
 */
@Component
public class TopicProduce {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Topic topic;

    public void sendMessage() {
        template.convertAndSend(topic, "SpringBoot-activeMq-topic-" + UUID.randomUUID().toString().substring(0, 8));
    }

    @Scheduled(fixedDelay = 3000)
    public void SendMessageSchedule() {
        sendMessage();
        System.out.println("topic send ok 3s...");
    }

}
