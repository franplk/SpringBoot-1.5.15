package com.plk.sbdemo.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MainPage, All Authentication Can Entry
 */
@Controller
public class HomeController {

	@RequestMapping("")
	public String home() {
		return "/index";
	}
	
	@RequestMapping("/index")
	public String homePage() {
		return "/index";
	}
}
