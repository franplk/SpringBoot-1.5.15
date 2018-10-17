package com.plk.sbdemo.quartz.web.listener;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.plk.sbdemo.quartz.jobs.QuartzJob;
import com.plk.sbdemo.quartz.jobs.annotation.JobConfig;
import com.plk.sbdemo.quartz.service.ScheduleService;

/**
 * 启动配置的定时任务
 */
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
			logger.info("没有定时任务需要启动");
			return;
		}
		jobMap.forEach((name, job) -> addToSchedule(name, job));
		logger.info("启动系统中配置的定时任务完成");
	}
	
	public void addToSchedule(String beanName, QuartzJob job) {
		Class<? extends QuartzJob> clazz = job.getClass();
		// 获取Job的配置注解
		JobConfig jobConfig = clazz.getAnnotation(JobConfig.class);
		if (jobConfig == null) {
			return;
		}
		
		/**
		 * 获取Job信息
		 * **/
		// 分组
		String group = jobConfig.group();
		if (StringUtils.isEmpty(group)) {// 包名作为任务的分组
			group = clazz.getPackage().getName();
		}
		// 名称
		String name = jobConfig.name();
		if (StringUtils.isEmpty(name)) {
			name = beanName;
		}
		// 定时表达式
		String cron = jobConfig.cron();
		if (StringUtils.isEmpty(cron)) {
			throw new RuntimeException("定时任务cron不能为空");
		}
		
		logger.info("启动定时任务[group={},name={},cron={}]", group, name, cron);
		schedulerService.addJob(group, name, cron, jobConfig.desc(), clazz);
	}
}
