package com.plk.sbdemo.admin.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.plk.sbdemo.admin.domain.BaseDomain;

@Entity
public class Permission extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true, length=32)
	private String permissionName;
	
	private String permissionDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}
}
