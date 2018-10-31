package com.plk.sbdemo.admin.service.menu;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.admin.domain.menu.Menu;
import com.plk.sbdemo.admin.domain.menu.MenuItem;
import com.plk.sbdemo.admin.repository.menu.MenuRepository;
import com.plk.sbdemo.admin.web.exception.menu.MenuException;

/**
 * 菜单Service
 */
@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	/**
	 * @param menu
	 * @return
	 */
	@CacheEvict("menuCache")
	public MenuItem addOrEditMenu(MenuItem menu) {
		if (null == menu.getId()) {
			menu.setCreateDate(new Date());
		} else {
			MenuItem lastMenu = menuInfo(menu.getId());
			menu.setCreateDate(lastMenu.getCreateDate());
		}
		menu.setUpdateDate(new Date());
		
		return menuRepository.save(menu);
	}

	/**
	 * 删除菜单以及其子菜单
	 * @param menuId
	 */
	@CacheEvict("menuCache")
	public void deleteMenu(Long menuId) {
		MenuItem menu = menuRepository.findOne(menuId);
		if (menu == null) {
			throw new MenuException("没有对应菜单项[" + menuId + "]");
		}
		menuRepository.deleteByIdOrParentId(menuId, menuId);
	}

	/**
	 * 获取菜单信息
	 * @param menuId
	 * @return
	 */
	@Cacheable("menuCache")
	public MenuItem menuInfo(Long menuId) {
		MenuItem menuItem = menuRepository.findOne(menuId);
		if (menuItem == null) {
			throw new MenuException("没有对应菜单项[" + menuId + "]");
		}
		return menuItem;
	}

	/**
	 * 获取用户的菜单组
	 */
	@Cacheable("menuCache")
	public List<Menu> menuList() {
		List<MenuItem> menuList = subMenuList(0L);
		return menuList.stream().map(m -> {
			List<MenuItem> children = subMenuList(m.getId());
			return new Menu().setParent(m).setChildren(children);
		}).collect(Collectors.toList());
	}

	/**
	 * 根据父菜单查询子菜单
	 * 
	 * @param parentId
	 * @return
	 */
	@Cacheable("menuCache")
	public List<MenuItem> subMenuList(Long parentId) {
		List<MenuItem> menuList = menuRepository.findByParentId(parentId);
		if (null == menuList) {
			menuList = Collections.emptyList();
		}
		return menuList;
	}
}
