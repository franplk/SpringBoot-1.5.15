package com.plk.sbdemo.shiro.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.shiro.domain.Permission;
import com.plk.sbdemo.shiro.domain.Role;
import com.plk.sbdemo.shiro.domain.RolePermission;
import com.plk.sbdemo.shiro.exception.UserRoleException;
import com.plk.sbdemo.shiro.repository.RolePermissionRepository;
import com.plk.sbdemo.shiro.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleDao;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RolePermissionRepository rolePermDao;
	
	public Role addRole(Role role) {
		return roleDao.save(role);
	}
	
	public String getRoleNameById(Long roleid) {
		Role role = roleDao.findOne(roleid);
		if (role == null) {
			throw new UserRoleException("没有该角色");
		}
		return role.getRoleName();
	}
	
	public List<Permission> getPermissonsByRoleId(Long roleid) {
		List<RolePermission> rolePermissions = rolePermDao.findByRoleid(roleid);
		if (null == rolePermissions || rolePermissions.isEmpty()) {
			return Collections.emptyList();
		}
		
		return rolePermissions.stream()
			.map(rp -> rp.getPermissionid())
			.map(p -> permissionService.findById(p))
			.collect(Collectors.toList());
	}
}
