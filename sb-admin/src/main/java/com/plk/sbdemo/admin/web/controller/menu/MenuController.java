package com.plk.sbdemo.admin.web.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plk.sbdemo.admin.domain.menu.MenuItem;
import com.plk.sbdemo.admin.service.menu.MenuService;
import com.plk.sbdemo.admin.web.ApiResponse;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 进入菜单列表页
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String menuList(Model model) {
		return "menu/list";
	}
	
	/**
	 * 查询菜单列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	public @ResponseBody ApiResponse menuList(Long parentId) {
		if (null == parentId) {
			parentId = 0L;
		}
		List<MenuItem> pMenuList = menuService.subMenuList(parentId);
		return ApiResponse.success(pMenuList);
	}

	/**
	 * 进入添加菜单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public String addMenu(Model model) {
		List<MenuItem> pMenuList = menuService.subMenuList(0L);
		model.addAttribute("pMenuList", pMenuList);
		return "menu/add";
	}
	
	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	@PostMapping("/addOrEdit")
	public @ResponseBody ApiResponse addMenu(MenuItem menu) {
		MenuItem menuItem = menuService.addOrEditMenu(menu);
		return ApiResponse.success(menuItem);
	}
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @return
	 */
	@PostMapping("/delete/{menuId}")
	public @ResponseBody ApiResponse deleteMenu(@PathVariable Long menuId) {
		menuService.deleteMenu(menuId);
		return ApiResponse.success();
	}
	
	/**
	 * 编辑菜单页面
	 * @param menuId
	 * @return
	 */
	@GetMapping("/edit/{menuId}")
	public String editMenu(Model model, @PathVariable Long menuId) {
		MenuItem menuItem = menuService.menuInfo(menuId);
		model.addAttribute("menuItem", menuItem);
		
		List<MenuItem> pMenuList = menuService.subMenuList(0L);
		model.addAttribute("pMenuList", pMenuList);
		
		return "menu/edit";
	}
}
