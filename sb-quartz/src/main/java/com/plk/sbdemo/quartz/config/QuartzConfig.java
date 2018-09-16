package com.plk.sbdemo.quartz.config;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {
	
	/**
	 * 自定义定时任务的JobFactory
	 */
	@Bean("myJobFactory")
	public JobFactory jobFactory(ApplicationContext context) {
		return new AdaptableJobFactory() {
			protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
				// 已经委托给Spring进行管理不需要再次实例直接在Spring管理的Bean中查找
				return context.getBean(bundle.getJobDetail().getJobClass());
			}
		};
	}
	
	/**
	 * 定义Spring的定时任务管理
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory myJobFactory) throws Exception {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		
		// 配置延迟以及自定义Job工厂
		schedulerFactoryBean.setStartupDelay(10);
		schedulerFactoryBean.setJobFactory(myJobFactory);
		
		return schedulerFactoryBean;
	}
}