package com.demo.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @desc 城市服务启动类 City service startup class
 **/
@SpringBootApplication
@EnableEurekaClient
public class CityServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityServerApplication.class,args);
    }

}
