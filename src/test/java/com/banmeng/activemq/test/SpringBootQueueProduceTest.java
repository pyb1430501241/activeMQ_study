package com.banmeng.activemq.test;

import com.banmeng.activemq.springboot.queue.QueueProduce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author 半梦
 * @create 2021-04-03 15:03
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SpringBootQueueProduceTest {

    @Autowired
    private QueueProduce produce;

    @Test
    public void testSend() {
        produce.sendMessage();
    }

    @Test
    public void testSendScheduled() {
        produce.sendMessageSchedule();
    }

}
