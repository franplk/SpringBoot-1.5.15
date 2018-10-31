package com.plk.sbdemo.admin.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.admin.domain.user.User;
import com.plk.sbdemo.admin.service.user.UserService;
import com.plk.sbdemo.admin.web.ApiResponse;

@Controller
@RequestMapping("/user")
public class UserApplyController {

	@Autowired
	private UserService userService;

	/**
	 * 用户申请页面
	 * 
	 * @return
	 */
	@GetMapping("/apply")
	public String apply() {
		return "user/apply";
	}

	/**
	 * 用户申请添加
	 * 
	 * @param username
	 * @param nickname
	 * @param email
	 * @return
	 */
	@ResponseBody
	@PostMapping("/apply")
	public ApiResponse applyUser(String username, String nickname, String email) {
		User user = userService.applyUser(username, nickname, email);
		return ApiResponse.success(user);
	}
}
