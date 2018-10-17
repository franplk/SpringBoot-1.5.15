package com.plk.sbdemo.quartz.web.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * 定时任务启动事件
 */
public class QuartzLaunchEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;

	public QuartzLaunchEvent(ApplicationContext context) {
		super(context);
		this.context = context;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
