package com.pt.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloServiceTests {
	
	@Autowired
	private HelloService userSerivce;

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void addUser() throws Exception {
		userSerivce.create("葛宏斌");
	}
	

}
