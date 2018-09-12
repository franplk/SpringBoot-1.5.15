package com.plk.sbdemo.schedule.config;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

	@Value("${schedule.pool.size}")
	private int corePoolSize = 10;
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
	}
}
