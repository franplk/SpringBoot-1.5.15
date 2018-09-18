package com.plk.sbdemo.mongodb.entity;

import java.io.Serializable;

public class UserAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long uid;
	private String userName;
	private String passWord;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
