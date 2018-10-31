package com.plk.sbdemo.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.plk.sbdemo.admin.config.quartz.event.QuartzLaunchEvent;

@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AdminApplication.class, args);
		
		// 启动定时任务事件
		context.publishEvent(new QuartzLaunchEvent(context));
	}
}
