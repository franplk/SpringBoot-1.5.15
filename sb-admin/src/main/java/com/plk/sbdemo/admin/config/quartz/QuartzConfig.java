package com.plk.sbdemo.admin.config.quartz;

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
	 * 定时任务执行时要获取一个实例，将这些实例通过Spring直接管理，通过获取bean实例的方式获取
	 */
	@Bean("myJobFactory")
	public JobFactory jobFactory(ApplicationContext context) {
		return new AdaptableJobFactory() {
			protected Object createJobInstance(TriggerFiredBundle bundle) {
				// 已经委托给Spring进行管理不需要再次实例直接在Spring管理的Bean中查找
				return context.getBean(bundle.getJobDetail().getJobClass());
			}
		};
	}
	
	/**
	 * 定义Spring的定时任务管理
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory myJobFactory) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		
		// 配置延迟以及自定义Job工厂
		schedulerFactoryBean.setStartupDelay(10);
		schedulerFactoryBean.setJobFactory(myJobFactory);
		
		return schedulerFactoryBean;
	}
}