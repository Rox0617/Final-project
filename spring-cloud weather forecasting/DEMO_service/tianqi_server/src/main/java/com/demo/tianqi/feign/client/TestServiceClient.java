package com.demo.tianqi.feign.client;

import com.demo.tianqi.feign.client.impl.TestServiceClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @desc  value 代表服务名称，配置文件中配置的，fallback是服务调用失败执行方法
 **/

@FeignClient(value = "test-service",fallback = TestServiceClientImpl.class)
public interface TestServiceClient {

    @RequestMapping(value = "/testRox", method = RequestMethod.GET)
    String testRox();
}

