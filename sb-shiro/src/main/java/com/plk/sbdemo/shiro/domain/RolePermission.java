package com.plk.sbdemo.shiro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RolePermission extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Long roleid;
	
	@Column(nullable = false)
	private Long permissionid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(Long permissionid) {
		this.permissionid = permissionid;
	}
}
