package com.plk.sbdemo.mybatis;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.plk.sbdemo.mybatis.domain.User;
import com.plk.sbdemo.mybatis.service.UserXMLService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisXMLTest {

	private static Logger logger = LoggerFactory.getLogger(MybatisXMLTest.class);

	@Autowired
	private UserXMLService userService;

	@Before
	public void setup() {
		Assert.notNull(userService, "userService is null");
	}

	//@Test
	public void addUser() {
		User user = new User();
		user.setUsername("franplk-3");
		user.setPassword("1234");
		user.setNickname("franplp");
		user.setEmail("franplk@126.com");
		userService.addUser(user);
	}

	@Test
	public void findUser() {
		List<User> userList = userService.userList();
		if (null == userList || userList.isEmpty()) {
			logger.info("No User Found");
			return;
		}
		userList.forEach(u -> {
			System.out.println(u);
		});
	}

	@After
	public void destroy() {

	}

}
