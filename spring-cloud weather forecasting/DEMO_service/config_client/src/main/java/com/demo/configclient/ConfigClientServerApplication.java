package com.demo.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @desc 配置中心客户端启动类Configure the central client startup class
 **/
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientServerApplication {

    /**
     * 启动之后访问http://localhost:30005/getConfig Post-startup access
     *
     * 打开命令行窗口，使用以下命令发送一个 POST
     * 请求刷新 Spring Cloud Config 30005 客户端，通知客户端配置文件已经修改，需要重新拉去配置。
     * 启动命令框发送请求
     *curl -X POST "http://localhost:30005/actuator/refresh"
     * 然后在刷新访问地址
     *http://localhost:30005/getConfig
     * @param args
     */

    /**
     * Post-startup access http://localhost:30005/getConfig Post-startup access
     *
     * Open a command line window and send a POST using the following command
     *  Binding Refresh Request Spring Cloud Config 30005 On the client, notifies the client that the configuration file has been modified and needs to be removed again.
     * Start the command box to send the request
     *curl -X POST "http://localhost:30005/actuator/refresh"
     * Then refresh the access address
     *http://localhost:30005/getConfig
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientServerApplication.class,args);
    }
}
