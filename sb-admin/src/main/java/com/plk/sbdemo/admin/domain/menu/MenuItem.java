package com.plk.sbdemo.admin.domain.menu;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.plk.sbdemo.admin.domain.BaseDomain;

/**
 * 菜单类
 */
@Entity
public class MenuItem extends BaseDomain {

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
	
	private Date updateDate;

	public MenuItem() {
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
