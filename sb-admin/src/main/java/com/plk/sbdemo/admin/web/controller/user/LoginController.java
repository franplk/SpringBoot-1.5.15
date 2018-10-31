package com.plk.sbdemo.admin.web.controller.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.admin.web.ApiResponse;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public ApiResponse verify(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		
		return ApiResponse.success();
	}
	
	@GetMapping("/logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "login";
	}
}
