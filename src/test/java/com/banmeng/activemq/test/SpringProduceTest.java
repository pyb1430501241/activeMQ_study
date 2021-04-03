package com.banmeng.activemq.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author 半梦
 * @create 2021-03-30 19:08
 */
public class SpringProduceTest {

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
    public void testSend() throws Exception {
        template.send(session -> session.createTextMessage("Spring 整合 ActiveMQ 03 !!!"));
        System.out.println("send task over");
    }

}
