package com.plk.sbdemo.admin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plk.sbdemo.admin.domain.menu.Menu;
import com.plk.sbdemo.admin.service.menu.MenuService;

@Controller
public class HomeController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/")
	public String index(ModelMap modelMap) {
		// Query Menu List
		List<Menu> menuList = menuService.menuList();
		modelMap.put("menuList", menuList);

		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "home/welcome";
	}
}
