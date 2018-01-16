package com.pt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pt.service.HelloService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloSerivce;
	
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    
    @RequestMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("发生错误");
    }
    
    @RequestMapping("/jdbc")
    public String jdbc() throws Exception {
    	helloSerivce.create("葛宏斌");
    	return "Success";
    }
}
