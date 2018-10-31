package com.plk.sbdemo.admin.domain.email;

import java.io.Serializable;

public class MailTemplate<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subject;// 邮件主题
	private String tplPath; // 对应模板的路径
	private String modelName;// 对应模板中属性对象的名称
	private T modelBean;// 对应的属性对象(一个模板对应一个类)

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTplPath() {
		return tplPath;
	}

	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public T getModelBean() {
		return modelBean;
	}

	public void setModelBean(T modelBean) {
		this.modelBean = modelBean;
	}
}
