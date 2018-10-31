package com.plk.sbdemo.admin.domain.menu;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单类
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	private MenuItem parent;
	private List<MenuItem> children;// child Menus

	public MenuItem getParent() {
		return parent;
	}

	public Menu setParent(MenuItem parent) {
		this.parent = parent;
		return this;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public Menu setChildren(List<MenuItem> children) {
		this.children = children;
		return this;
	}
}
