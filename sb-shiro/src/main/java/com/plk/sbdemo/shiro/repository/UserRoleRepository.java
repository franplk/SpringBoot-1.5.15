package com.plk.sbdemo.shiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.shiro.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	List<UserRole> findByUserid(Long userid);

}
