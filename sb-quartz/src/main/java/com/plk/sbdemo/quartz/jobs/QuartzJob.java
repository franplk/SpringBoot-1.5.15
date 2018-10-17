package com.plk.sbdemo.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 继承QuartzJob
 * 设置定时任务的名称，组名以及Cron表达式即可
 * 方便加入Quartz的管理
 * 1. 通过@Component注解加入Spring管理设置bean名称作为任务名称
 * 2. 复写jobCronExp方法设置crontab表达式
 * 3. 实现executeInternal方法完成任务
 */
public abstract class QuartzJob implements Job {

	/* 
	 * 实际执行任务
	 */
	@Override
	public final void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			executeInternal(context);
		} catch (Exception e) {
			doException(e);// 做异常处理
		} finally {
			doComplete();// 执行完成处理
		}
	}
	
	/**
	 * 定时任务的的实现
	 */
	public abstract void executeInternal(JobExecutionContext context);
	
	/**
	 * 执行任务过程出现异常的处理
	 */
	public void doException(Exception e) {
		e.printStackTrace();
	}
	
	/**
	 * 定时任务执行完成后的操作
	 */
	public void doComplete() {
	}
}
