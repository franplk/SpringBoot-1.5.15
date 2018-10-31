package com.plk.sbdemo.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plk.sbdemo.shiro.domain.Role;
import com.plk.sbdemo.shiro.model.ApiResponse;
import com.plk.sbdemo.shiro.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleServie;
	
	@RequestMapping("/add")
	public ApiResponse addRole(Role role) {
		roleServie.addRole(role);
		return ApiResponse.sucessResponse();
	}
}
