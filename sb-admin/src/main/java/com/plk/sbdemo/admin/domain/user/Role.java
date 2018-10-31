package com.plk.sbdemo.admin.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.plk.sbdemo.admin.domain.BaseDomain;

@Entity
public class Role extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true, length=32)
	private String roleName;
	
	@Column(length = 64)
	private String roleDesc;
	
	@ManyToMany
	@JoinTable(
		name = "r_role_permission",
		joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
	)
	private List<Permission> permissionList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
}
