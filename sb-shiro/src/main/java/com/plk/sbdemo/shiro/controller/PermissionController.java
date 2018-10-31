package com.plk.sbdemo.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plk.sbdemo.shiro.domain.Permission;
import com.plk.sbdemo.shiro.model.ApiResponse;

@Controller
@RequestMapping("/permit")
public class PermissionController {
	
	@RequestMapping("/add")
	public ApiResponse addPermission(Permission perm) {
		return ApiResponse.sucessResponse();
	}
}
