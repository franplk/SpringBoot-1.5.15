package com.plk.sbdemo.shiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.shiro.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
