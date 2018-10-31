package com.plk.sbdemo.shiro.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.shiro.domain.User;
import com.plk.sbdemo.shiro.domain.UserRole;
import com.plk.sbdemo.shiro.model.ApiResponse;
import com.plk.sbdemo.shiro.service.UserRoleService;
import com.plk.sbdemo.shiro.service.UserService;

/**
 * User Account Manager
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/list")
	public String userList(Model model) {
		List<User> userList = userService.findUser();
		model.addAttribute("userList", userList);

		return "/user/list";
	}

	@ResponseBody
	@PostMapping("/delete")
	@RequiresPermissions(value = {"admin", "user-delete"}, logical = Logical.OR)
	public ApiResponse delUser(Long userid) {
		userService.delUser(userid);
		return ApiResponse.sucessResponse();
	}

	@ResponseBody
	@PostMapping("/freeze")
	@RequiresPermissions(value = {"admin", "user-freeze"}, logical = Logical.OR)
	public ApiResponse freezeUser(Long userid) {
		userService.freezeUser(userid);
		return ApiResponse.sucessResponse();
	}
	
	
	@ResponseBody
	@PostMapping("/addRole")
	@RequiresPermissions(value = {"admin"})
	public ApiResponse addRole(UserRole userRole) {
		userRoleService.addRoleToUser(userRole);
		return ApiResponse.sucessResponse();
	}
}
