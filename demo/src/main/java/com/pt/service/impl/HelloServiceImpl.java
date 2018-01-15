package com.pt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pt.service.HelloService;

@Service("helloSerivce")
public class HelloServiceImpl implements HelloService{

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create(String name) {
		jdbcTemplate.update("insert into user(name) values(?)", name);
	}

}
