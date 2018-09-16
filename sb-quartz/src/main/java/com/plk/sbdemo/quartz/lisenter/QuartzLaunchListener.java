package com.plk.sbdemo.quartz.lisenter;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.plk.sbdemo.quartz.jobs.QuartzJob;
import com.plk.sbdemo.quartz.service.ScheduleService;

@Component
public class QuartzLaunchListener implements ApplicationListener<QuartzLaunchEvent> {

	private Logger logger = LoggerFactory.getLogger(QuartzLaunchListener.class);
	
	@Autowired
	private ScheduleService schedulerService;
	
	@Override
	public void onApplicationEvent(QuartzLaunchEvent event) {
		ApplicationContext context = event.getContext();
		Map<String, QuartzJob> jobMap = context.getBeansOfType(QuartzJob.class);
		if(jobMap == null || jobMap.isEmpty()) {
			logger.info("没有定时任务需要启动-----");
			return;
		}
		logger.info("启动系统中配置的定时任务-----" + jobMap.size());
		jobMap.forEach((name, job) -> {
			addToSchedule(name, job);
		});
		logger.info("启动系统中配置的定时任务完成-----");
	}
	
	public void addToSchedule(String beanName, QuartzJob job) {
		String groupName = job.getClass().getName();
		schedulerService.addJob(groupName, beanName, job);
	}
}
