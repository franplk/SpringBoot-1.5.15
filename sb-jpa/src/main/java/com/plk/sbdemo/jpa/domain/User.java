package com.plk.sbdemo.jpa.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public User() {
		this.regtime = new Timestamp(System.currentTimeMillis());
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true, length = 32)
	private String username;

	@Column(nullable = false, length = 128)
	private String password;

	@Column(nullable = true, length = 128)
	private String email;

	@Column(nullable = true, length = 32)
	private String nickname;

	@Column(nullable = false, length = 19)
	private Timestamp regtime;

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
