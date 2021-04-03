package com.banmeng.activemq.springboot.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * SpringBoot 整合 activeMq 队列生产者
 * @author 半梦
 * @create 2021-04-03 16:42
 */
@Component
public class QueueProduce {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    public void sendMessage() {
        template.convertAndSend(queue, "SpringBoot-activeMq-queue-" + UUID.randomUUID().toString().substring(0, 8));
    }

    @Scheduled(fixedDelay = 3000)
    public void sendMessageSchedule() {
        sendMessage();
        System.out.println("queue send ok 3s...");
    }

}
