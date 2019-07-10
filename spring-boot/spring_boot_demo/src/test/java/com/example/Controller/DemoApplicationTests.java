package com.example.Controller;

import com.example.DemoApplication;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		System.out.println("test");
	}

	@Test
	public void getUsersTest(){
		List users = userService.getUsers();
		System.out.println(users);
	}


}
