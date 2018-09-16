package com.plk.sbdemo.quartz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plk.sbdemo.quartz.domain.Menu;
import com.plk.sbdemo.quartz.service.MenuService;

@Controller
public class HomeController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String homePage(ModelMap modelMap) {

		// Query Menu List
		List<Menu> menuList = menuService.getPageMenu();
		modelMap.put("menuList", menuList);

		return "home/index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "/home/welcome";
	}
}
