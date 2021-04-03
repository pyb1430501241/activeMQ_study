package com.banmeng.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 半梦
 * @create 2021-04-03 15:04
 */
@SpringBootApplication
@EnableScheduling
public class ActiveMqRun {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqRun.class, args);
    }

}
