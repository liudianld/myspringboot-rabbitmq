package com.liudian.springboot.helloworld.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liudian.springboot.helloworld.exception.BusinessException;

@RestController
public class HelloWorldController {
	
	@Value("${liudian.msg}")
	private String msg;
	
	@RequestMapping("/hello")
	public String index() {
//		int num = 1 / 0;
		throw new BusinessException("100", "用户名密码错误");
//		return msg;
	}
}
