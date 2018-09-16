package com.plk.sbdemo.quartz.lisenter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * 定时任务启动监听器
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
