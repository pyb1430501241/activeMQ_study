package com.banmeng.activemq.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author 半梦
 * @create 2021-03-30 18:56
 */
public class SpringConsumerTest {

    private ApplicationContext context;

    private JmsTemplate template;

    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(com.banmeng.activemq.spring.ActiveMQConfig.class);
        template = context.getBean(JmsTemplate.class);
    }

    @Test
    public void test() throws Exception{
        Object o = context.getBean("destinationQueue");
        System.out.println(((ActiveMQQueue)o).getQueueName());
    }

    @Test
    public void testReceive() throws Exception {
        Message receive = template.receive();
        if(receive instanceof TextMessage) {
            System.out.println(((TextMessage) receive).getText());
        }
        System.out.println("receive task over");
    }

}
