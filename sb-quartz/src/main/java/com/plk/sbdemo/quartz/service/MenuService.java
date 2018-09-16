package com.plk.sbdemo.quartz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.plk.sbdemo.quartz.domain.Menu;

/**
 * 菜单Service
 */
@Service
public class MenuService {

	/**
	 * 获取用户的菜单组
	 */
	public List<Menu> getPageMenu() {
		List<Menu> menuList = new ArrayList<Menu>(2);

		Menu jobMenu = new Menu().setName("定时任务")
				.addSubMenu(new Menu("任务列表", "/quartz"));
		menuList.add(jobMenu);

		return menuList;
	}
}
