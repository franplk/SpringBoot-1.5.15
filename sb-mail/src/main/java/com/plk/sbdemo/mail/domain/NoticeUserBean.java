package com.plk.sbdemo.mail.domain;

import java.io.Serializable;

public class NoticeUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mailUser;

	public String getMailUser() {
		return mailUser;
	}

	public NoticeUserBean setMailUser(String mailUser) {
		this.mailUser = mailUser;
		return this;
	}
}
