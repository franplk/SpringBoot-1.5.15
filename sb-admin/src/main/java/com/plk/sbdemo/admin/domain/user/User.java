package com.plk.sbdemo.admin.domain.user;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.plk.sbdemo.admin.constants.UserConstants;
import com.plk.sbdemo.admin.domain.BaseDomain;

@Entity
public class User extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	public User() {
		this.status = UserConstants.STSTUS_NEWER;
		this.regtime = new Timestamp(System.currentTimeMillis());
	}
	
	public User(String username, String email) {
		this();
		this.username = username;
		this.email = email;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true, length = 32)
	private String username;

	@Column(nullable = false, length = 128)
	private String password;

	@Column(length = 128)
	private String email;

	@Column(length = 32)
	private String nickname;

	@Column(nullable = false, length = 19)
	private Timestamp regtime;
	
	private int status;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "r_user_role",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private List<Role> roleList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Timestamp getRegtime() {
		return regtime;
	}

	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		StringBuffer userInfo = new StringBuffer();
		
		userInfo.append("user[");
		userInfo.append("name=").append(this.username).append(",");
		userInfo.append("regtime=").append(this.regtime);
		userInfo.append("]");
		
		return userInfo.toString();
	}
}
