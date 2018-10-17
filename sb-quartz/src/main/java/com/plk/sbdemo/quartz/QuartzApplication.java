package com.plk.sbdemo.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.plk.sbdemo.quartz.web.listener.QuartzLaunchEvent;

/**
 *  Spring集成Quartz的方式
 *  可以对定时任务进行管理
 */
@SpringBootApplication
public class QuartzApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(QuartzApplication.class, args);
		
		// 触发定时任务初始化事件
		context.publishEvent(new QuartzLaunchEvent(context));
	}
}
