package com.plk.sbdemo.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.shiro.model.ApiResponse;
import com.plk.sbdemo.shiro.service.UserService;

/**
 * User Account Manager
 */
@Controller
@RequestMapping("/password")
public class PasswordController {
	
	@Autowired
	private UserService userService;

	/**
	 * 用户修改密码
	 * @return
	 */
	@GetMapping("")
	public String updatePassword() {
		return "/user/setPassword";
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	@PostMapping("/reset")
	@ResponseBody
	public ApiResponse resetPassword() {
		userService.freezeUser(1L);
		return ApiResponse.sucessResponse();
	}
}
