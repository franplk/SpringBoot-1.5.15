package com.plk.sbdemo.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * 继承QuartzJob
 * 设置定时任务的名称，组名以及Cron表达式即可
 * 方便加入Quartz的管理
 */
@Component("MyQuartzJob")
public class MyQuartzJob extends QuartzJob {
	
	@Override
	public String jobCronExp() {
		return "0 0/5 * * * ?";
	}

	@Override
	public void executeInternal(JobExecutionContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.err.println(sdf.format(new Date()) + "---A New Job Is Running...");
	}
	
	@Override
	public void definitionJobInfo() {
		setJobDescription("测试类");
	}
}
