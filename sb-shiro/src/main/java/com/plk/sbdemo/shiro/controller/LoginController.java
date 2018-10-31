package com.plk.sbdemo.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Login Manager
 */
@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String loginPage() {
		return "/login";
	}
	
	@PostMapping("/verify")
	public String login(RedirectAttributes redirectAttr, String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			redirectAttr.addFlashAttribute("errormsg", e.getMessage());
			return "redirect:/login";
		}
		return "redirect:/index";
	}

	@GetMapping("/logout")
	public String logout() {
		try {
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			logger.error("Logout Failed:{}", e.getMessage());
		}
		return "/login";
	}
}