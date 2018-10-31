package com.plk.sbdemo.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plk.sbdemo.shiro.domain.User;
import com.plk.sbdemo.shiro.service.UserService;

@Controller
@RequestMapping("/newer")
public class NewerController {

	@Autowired
	private UserService userService;
	
	/**
	 * 用户申请页面
	 * @return
	 */
	@GetMapping("/apply")
	public String apply() {
		return "/user/apply";
	}
	
	/**
	 * 用户申请添加
	 * @param username
	 * @param nickname
	 * @param email
	 * @return
	 */
	@PostMapping("/apply/add")
	public String applyUser(Model model, String username, String nickname, String email) {
		User user = userService.applyUser(username, nickname, email);
		model.addAttribute("user", user);
		return "/user/applySuccess";
	}
}
