package com.plk.sbdemo.admin.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.admin.service.user.AccountService;
import com.plk.sbdemo.admin.web.ApiResponse;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/pass/update")
	public String passUpdate(Model model) {
		return "user/passUpdate";
	}
	
	@ResponseBody
	@PostMapping("/pass/update")
	public ApiResponse passUpdate(Long userid, String newPass, String repPass) {
		accountService.passUpdate(userid, newPass, repPass);
		return ApiResponse.success();
	}
}
