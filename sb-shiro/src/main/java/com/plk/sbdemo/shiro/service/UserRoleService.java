package com.plk.sbdemo.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.shiro.domain.UserRole;
import com.plk.sbdemo.shiro.repository.UserRoleRepository;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public void addRoleToUser(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
	
	public List<UserRole> findUserRole(Long userid) {
		return userRoleRepository.findByUserid(userid);
	}
}
