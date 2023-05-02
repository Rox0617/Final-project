package com.demo.tianqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @desc
 **/
@EnableFeignClients("com.demo.tianqi.*")  //启用feign客户端
@EnableEurekaClient  //让注册中心发现、并扫描到该服务
@SpringBootApplication(scanBasePackages = {"com.demo"})
public class TianQiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TianQiServiceApplication.class,args);
    }
}
