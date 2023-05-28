package com.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @desc网关服务启动类 Gateway service startup class
 **/
@EnableEurekaClient
@SpringBootApplication
public class GateWayApplication {


    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }
}
