package com.plk.sbdemo.quartz.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单类
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 6401042065040003502L;

	private String name;
	private String url;
	private List<Menu> subMenuList;// child Menus
	
	public Menu(){
	}
	
	public Menu(String name){
		this.name = name;
	}
	
	public Menu(String name, String url){
		this.name = name;
		this.url = url;
	}
	
	public Menu(String name, List<Menu> subMenuList){
		this.name = name;
		this.subMenuList = subMenuList;
	}

	public String getName() {
		return name;
	}

	public Menu setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Menu setUrl(String url) {
		this.url = url;
		return this;
	}

	public List<Menu> getSubMenuList() {
		return subMenuList;
	}

	public Menu addSubMenu(Menu menu) {
		if (subMenuList == null) {
			subMenuList = new ArrayList<Menu>();
		}
		subMenuList.add(menu);
		return this;
	}
	
	public Menu setSubMenu(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
		return this;
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		
		info.append("[");
		info.append("Name=").append(name).append(";");
		info.append("URL=").append(url).append(";");
		info.append("]");
		
		return info.toString();
	}
}
