package com.banmeng.activemq.spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.*;

/**
 * Spring 整合 ActiveMQ
 * @author 半梦
 * @create 2021-03-30 18:47
 */
//@Configuration
@SuppressWarnings("all")
public class ActiveMQConfig {

    @Bean
    public PooledConnectionFactory jmsFactory(){
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setConnectionFactory(new ActiveMQConnectionFactory());
        factory.setMaxConnections(100);
        return factory;
    }

    @Bean
    public ActiveMQTopic destinationTopic() {
        return new ActiveMQTopic("spring-active-topic");
    }

    @Bean
    public JmsTemplate jmsTemplate(PooledConnectionFactory factory, Destination destination) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(factory);
        jmsTemplate.setDefaultDestination(destination);
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer jmsListenerContainer(ConnectionFactory factory, Destination destination) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setDestination(destination);
        container.setMessageListener((MessageListener)(message -> {
            if(message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    System.out.println(e.getMessage());
                }
            }
        }));
        return container;
    }


}
