package com.plk.sbdemo.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.jpa.domain.User;
import com.plk.sbdemo.jpa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userDao;
	
	public User addUser(User user) {
		return userDao.save(user);
	}
	
	public List<User> userList() {
		return userDao.findAll();
	}
}
