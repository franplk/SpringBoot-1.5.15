package com.plk.sbdemo.quartz.exception.quartz;

public class QuartzException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QuartzException() {
	}

	public QuartzException(String msg) {
		super(msg);
	}

}
