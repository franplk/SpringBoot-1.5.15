package com.plk.sbdemo.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.jpa.domain.UserAccount;
import com.plk.sbdemo.jpa.repository.UserRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserRepository userDao;
	
	public List<UserAccount> userList() {
		
		return userDao.findAll();
		
	}
	
}
