package com.demo.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @desc 测试远程调用 Test remote call
 **/
@RequestMapping("/test")
@Controller
public class TestRoxController {


    /**
     * 测试Feign远程调用方法 Test the Feign remote call method
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/testRox")
    @ResponseBody
    public String testRox(){
        return "测试Feign远程调用方法";
    }
}
