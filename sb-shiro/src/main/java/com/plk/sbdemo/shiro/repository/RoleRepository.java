package com.plk.sbdemo.shiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plk.sbdemo.shiro.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
