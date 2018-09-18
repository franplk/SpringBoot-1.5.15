package com.plk.sbdemo.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.mybatis.domain.User;
import com.plk.sbdemo.mybatis.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userDao;
	
	public void addUser(User user) {
		userDao.addEntity(user);
	}
	
	public List<User> userList() {
		return userDao.findAll();
	}
}
