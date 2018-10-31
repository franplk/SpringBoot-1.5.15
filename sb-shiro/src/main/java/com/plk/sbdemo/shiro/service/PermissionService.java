package com.plk.sbdemo.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.shiro.domain.Permission;
import com.plk.sbdemo.shiro.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionDao;
	
	public Permission findById(Long id) {
		return permissionDao.findOne(id);
	}
}
