package com.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @desc 配置中心启动类 Configure the central startup class
 **/
@EnableConfigServer   //@EnableConfigServer注解激活配置中心功能
                      //@EnableConfigServer Annotations enable the configuration center function
@EnableEurekaClient
@SpringBootApplication
public class ConfigServerApplication {

    /**
     * 启动后浏览器访问地址就可了
     * After startup, the browser can access the address
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }

}
