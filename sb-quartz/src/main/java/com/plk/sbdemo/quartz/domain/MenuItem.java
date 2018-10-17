package com.plk.sbdemo.quartz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 菜单类
 */
@Entity
public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private Long parentId;
	
	@Column(length = 32)
	private String name;
	
	@Column(length = 128)
	private String url;
	
	@Column(length = 16)
	private String icon;
	
	@Column(length = 128)
	private String descInfo;
	
	private Date createDate;

	public MenuItem() {
		this.parentId = 0L;
		this.createDate = new Date();
	}

	public MenuItem(String name) {
		this();
		this.name = name;
	}

	public MenuItem(String name, String url) {
		this();
		this.name = name;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public MenuItem setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public MenuItem setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public MenuItem setUrl(String url) {
		this.url = url;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
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
