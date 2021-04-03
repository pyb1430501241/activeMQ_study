package com.banmeng.activemq.broker;

import org.apache.activemq.Service;

/**
 * 内置的 activeMQ 服务
 * @author 半梦
 * @create 2021-03-30 18:32
 */
public class BrokerService implements Service {

    private org.apache.activemq.broker.BrokerService brokerService;

    public BrokerService() {
        this.brokerService = new org.apache.activemq.broker.BrokerService();
    }

    @Override
    public void start() throws Exception {
        brokerService.start();
    }

    @Override
    public void stop() throws Exception {
        brokerService.stop();
    }

    public void setUseJmx(boolean useJmx) {
        brokerService.setUseJmx(useJmx);
    }

}
