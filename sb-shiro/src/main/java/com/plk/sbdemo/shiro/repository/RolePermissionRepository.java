package com.plk.sbdemo.shiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.shiro.domain.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

	List<RolePermission> findByRoleid(Long roleid);
	
}
