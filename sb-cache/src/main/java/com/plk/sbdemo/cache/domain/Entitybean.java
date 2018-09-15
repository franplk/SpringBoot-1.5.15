package com.plk.sbdemo.cache.domain;

import java.io.Serializable;

public class Entitybean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	
	public Entitybean() {}
	
	public Entitybean(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("[")
				.append("name=").append(name).append(",")
				.append("value=").append(value)
				.append("]")
				.toString();
	}
}
