package com.demo.tianqi.feign.client.impl;

import com.demo.tianqi.feign.client.TestServiceClient;
import org.springframework.stereotype.Component;

/**
 * @desc
 **/
@Component
public class TestServiceClientImpl implements TestServiceClient {

    /**
     * Feign自带容错机制，已经整合Hystrix
     * Feign comes with its own fault tolerance mechanism and has integrated Hystrix
     * @return
     */
    @Override
    public String testRox() {
        System.out.println("调用测试服务出错,启动熔断机制。");
        return "调用测试服务出错,启动熔断机制。";
    }
}