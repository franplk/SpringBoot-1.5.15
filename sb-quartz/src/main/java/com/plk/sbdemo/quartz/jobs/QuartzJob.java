package com.plk.sbdemo.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class QuartzJob implements Job {

	protected String jobDescription;
	
	public QuartzJob() {
		definitionJobInfo();
	}

	@Override
	public final void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			executeInternal(context);
		} catch (Exception e) {
			// 做异常处理
			e.printStackTrace();
		} finally {
			doComplete();
		}
	}

	/**
	 * 定义定时任务的基本信息
	 */
	public void definitionJobInfo() {
	}
	
	/**
	 * 设置Cron表达式的生成
	 */
	public abstract String jobCronExp();
	
	/**
	 * 定时任务的的实现
	 */
	public abstract void executeInternal(JobExecutionContext context);
	
	/**
	 * 定时任务执行完成后的操作
	 */
	public void doComplete() {
		
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}
