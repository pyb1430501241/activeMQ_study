package com.banmeng.activemq.test;

import com.banmeng.activemq.springboot.queue.QueueConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author 半梦
 * @create 2021-04-03 15:02
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SpringBootQueueConsumerTest {

    @Autowired
   private QueueConsumer consumer;

    @Test
    public void testReceive() {
       consumer.receive();
    }

}
