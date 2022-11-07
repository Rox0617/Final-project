package com.study.spring.cloud.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//The controller used to process the rest request
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
}
