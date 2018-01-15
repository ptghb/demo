package com.pt.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTests {
	
	private MockMvc mvc;
	
	@Autowired
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	
	@Test
	public void addUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/users/add").param("id", "1") 
				.param("name", "葛宏斌"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void updUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/users/upd").param("id", "1") 
				.param("name", "葛宏斌"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"code\":0,\"message\":null,\"url\":null,\"data\":{\"id\":1,\"name\":\"葛宏斌\"}}")));
	}
	
	@Test
	public void getUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"code\":0,\"message\":null,\"url\":null,\"data\":[{\"id\":1,\"name\":\"葛宏斌\"},{\"id\":2,\"name\":\"葛宏斌\"}]}")));
	}

}
