package com.demo.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @desc 读取配置中心指定配置文件的内容，并展示到页面
 * Read the content of the configuration file specified by the configuration center and display it to the page
 **/
@RefreshScope //为了让动态（手动）的获取最新的git 配置，在添加 actuator 监控加载 RefreshScope
              // To get the latest git configuration dynamically (manually), add actuator to monitor load RefreshScope,
@Controller
@RequestMapping("/configclient")
public class ConfigClientController {

    @Value("${config.info}")
    private String configInf;

    @Value("${test.tianqi}")
    private String testTq;

    @RequestMapping(value = "/getConfig",method = RequestMethod.GET)
    @ResponseBody
    public String getConfig(){
        return "info======"+configInf+"天气："+testTq;
    }

}
